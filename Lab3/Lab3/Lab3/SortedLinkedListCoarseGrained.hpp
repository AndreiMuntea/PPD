#ifndef _SORTED_LINKED_LIST_COARSE_GRAINED_HPP_
#define _SORTED_LINKED_LIST_COARSE_GRAINED_HPP_

#include <mutex>
#include <sal.h>

template <class T>
class SortedLinkedListCoarseGrained
{
private:
	class Node
	{
	public:
		Node* next = nullptr;
		T value;

		Node(
			_In_ const T& Value
		);

		~Node() = default;
	};

public:
	SortedLinkedListCoarseGrained() = default;

	~SortedLinkedListCoarseGrained();

	SortedLinkedListCoarseGrained(
		_In_ const SortedLinkedListCoarseGrained<T>& Other
	) = delete;

	void 
	Insert(
		_In_ const T& Element
	);

	void 
	Delete(
		_In_ const T& Element
	);

	class Iterator
	{
		friend class SortedLinkedListCoarseGrained<T>;
	public:
		Iterator(
			_In_ SortedLinkedListCoarseGrained<T>* Parent
		);

		~Iterator();

		bool
		IsValid();

		T
		GetNext();

	private:
		SortedLinkedListCoarseGrained<T>* parent;
		Node* current;
	};

	Iterator 
	GetIterator()
	{
		return Iterator{ this };
	}

private:

	Node* head = nullptr;
	std::mutex guard;
};


#endif //_SORTED_LINKED_LIST_COARSE_GRAINED_HPP_

template<class T>
inline 
SortedLinkedListCoarseGrained<T>::~SortedLinkedListCoarseGrained()
{
	guard.lock();
	Node* temp = nullptr;

	while (this->head != nullptr)
	{
		temp = this->head->next;
		delete(this->head);
		this->head = temp;
	}

	this->head = nullptr;
	guard.unlock();
}

template<class T>
inline 
void 
SortedLinkedListCoarseGrained<T>::Insert(
	_In_ const T & Element
)
{
	guard.lock();
	Node* node = new Node(Element);
	Node* previous = this->head;
	Node* current = this->head;

	// Empty list
	if (this->head == nullptr)
	{
		this->head = node;
		goto Exit;
	}

	// We need to insert at head
	if (Element <= this->head->value)
	{
		node->next = this->head;
		this->head = node;
		goto Exit;
	}

	// We need to insert somewhere on the list
	while (current != nullptr && Element > current->value)
	{
		previous = current;
		current = current->next;
	}

	node->next = previous->next;
	previous->next = node;

Exit:
	guard.unlock();
}

template<class T>
inline 
void 
SortedLinkedListCoarseGrained<T>::Delete(
	_In_ const T & Element
)
{
	guard.lock();
	Node* previous = this->head;
	Node* current = this->head;

	// Empty list
	if (this->head == nullptr)
	{
		goto Exit;
	}

	// We need to delete head
	if (this->head->value == Element)
	{
		current = this->head->next;
		delete this->head;
		this->head = current;
		goto Exit;
	}

	// Search for the element
	while (current != nullptr && current->value != Element)
	{
		previous = current;
		current = current->next;
	}

	// We didn't find the element
	if (current == nullptr)
	{
		goto Exit;
	}

	previous->next = current->next;
	delete current;

Exit:
	guard.unlock();
}

template<class T>
inline
SortedLinkedListCoarseGrained<T>::Node::Node(
	_In_ const T & Value
) :
	next{ nullptr },
	value{ Value }
{}

template<class T>
inline SortedLinkedListCoarseGrained<T>::Iterator::Iterator(
	_In_ SortedLinkedListCoarseGrained<T>* Parent
)
{
	Parent->guard.lock();
	this->parent = Parent;
	this->current = this->parent->head;
}

template<class T>
inline SortedLinkedListCoarseGrained<T>::Iterator::~Iterator()
{
	parent->guard.unlock();
}

template<class T>
inline bool SortedLinkedListCoarseGrained<T>::Iterator::IsValid()
{
	return (current != nullptr);
}

template<class T>
inline T SortedLinkedListCoarseGrained<T>::Iterator::GetNext()
{
	T value = current->value;
	current = current->next;
	return value;
}
