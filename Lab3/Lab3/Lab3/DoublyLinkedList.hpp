#ifndef _DOUBLY_LINKED_LIST_HPP_
#define _DOUBLY_LINKED_LIST_HPP_

#include <sal.h>
#include <shared_mutex>


template <class T>
class DoublyLinkedList
{
protected:
	class Node
	{
	public:
		Node() = default;
		
		Node(
			_In_ _Const_ const T& Value
		);
		
		virtual ~Node();

		void 
		Lock();

		void 
		Unlock();

		Node* Next = nullptr;
		Node* Previous = nullptr;
		std::mutex Guard;
		T Value;
	};

public:
	virtual ~DoublyLinkedList();

	class Iterator
	{
		friend class DoublyLinkedList<T>;

	public:
		Iterator(
			_In_ DoublyLinkedList<T>* Parent
		);

		~Iterator();

		bool
		IsValid();

		T
		GetNext();

	private:
		DoublyLinkedList<T>* Parent;
		Node* Current;
	};

	Iterator 
	GetIterator() 
	{
		return Iterator{ this };
	}

	virtual 
	void 
	Insert(
		_In_ _Const_ const T& Element
	) = 0;

	virtual 
	void 
	Delete(
		_In_ _Const_ const T& Element
	) = 0;

protected:
	// Constructor available only for class that inherit this one
	DoublyLinkedList();

	virtual
	void
	AcquireSharedLock();

	virtual 
	void 
	ReleaseSharedLock();

	virtual 
	void 
	AcquireExclusiveLock();

	virtual 
	void 
	ReleaseExclusiveLock();

	Node* Head = nullptr;
	Node* Tail = nullptr;
	std::shared_mutex Guard;
};

#endif //_DOUBLY_LINKED_LIST_HPP_

template<class T>
inline DoublyLinkedList<T>::~DoublyLinkedList()
{
	this->AcquireExclusiveLock();
	Node* current = this->Head;

	while (current != nullptr)
	{
		this->Head = this->Head->Next;
		delete current;
		current = this->Head;
	}

	this->ReleaseExclusiveLock();
}

template<class T>
inline DoublyLinkedList<T>::DoublyLinkedList() :
	Guard{}
{
	this->Head = new Node();
	this->Tail = new Node();
	this->Head->Next = Tail;
	this->Tail->Previous = Head;
}

template<class T>
inline void DoublyLinkedList<T>::AcquireSharedLock()
{
	this->Guard.lock_shared();
}

template<class T>
inline void DoublyLinkedList<T>::ReleaseSharedLock()
{
	this->Guard.unlock_shared();
}

template<class T>
inline void DoublyLinkedList<T>::AcquireExclusiveLock()
{
	this->Guard.lock();
}

template<class T>
inline void DoublyLinkedList<T>::ReleaseExclusiveLock()
{
	this->Guard.unlock();
}

template<class T>
inline DoublyLinkedList<T>::Iterator::Iterator(DoublyLinkedList<T>* Parent)
{
	Parent->AcquireExclusiveLock();
	this->Parent = Parent;
	this->Current = Parent->Head->Next;
}

template<class T>
inline DoublyLinkedList<T>::Iterator::~Iterator()
{
	this->Parent->ReleaseExclusiveLock();
}

template<class T>
inline bool DoublyLinkedList<T>::Iterator::IsValid()
{
	return (Current != Parent->Tail);
}

template<class T>
inline T DoublyLinkedList<T>::Iterator::GetNext()
{
	T value = Current->Value;
	Current = Current->Next;
	return value;
}

template<class T>
inline DoublyLinkedList<T>::Node::Node(const T & Value) :
	Value{ Value },
	Guard{},
	Next{ nullptr },
	Previous{ nullptr }
{
}

template<class T>
inline DoublyLinkedList<T>::Node::~Node()
{
	this->Lock();
	this->Unlock();
}

template<class T>
inline void DoublyLinkedList<T>::Node::Lock()
{
	this->Guard.lock();
}

template<class T>
inline void DoublyLinkedList<T>::Node::Unlock()
{
	this->Guard.unlock();
}
