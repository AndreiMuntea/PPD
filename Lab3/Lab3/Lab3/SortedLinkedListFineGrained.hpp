#ifndef _SORTED_LINKED_LIST_FINE_GRAINED_HPP_
#define _SORTED_LINKED_LIST_FINE_GRAINED_HPP_

#include "DoublyLinkedList.hpp"

template <class T>
class SorteLinkedListFineGrained : public DoublyLinkedList<T>
{
private:
public:
	SorteLinkedListFineGrained() = default;

	virtual
	~SorteLinkedListFineGrained() = default;

	virtual
	void
	Insert(
		_In_ _Const_ const T& Element
	) override;

	virtual
	void
	Delete(
		_In_ _Const_ const T& Element
	) override;
};

template<class T>
inline 
void 
SorteLinkedListFineGrained<T>::Insert(
	_In_ _Const_ const T & Element
)
{
	this->AcquireSharedLock();
	
	// We are sure that there are at least 2 nodes (head and tail)
	this->Head->Lock();
	this->Head->Next->Lock();

	Node* previous = this->Head;
	Node* current = this->Head->Next;
	Node* node = new Node(Element);

	while (current != this->Tail && Element > current->Value)
	{
		current->Next->Lock();
		previous->Unlock();
		
		previous = current;
		current = current->Next;
	}

	previous->Next = node;
	current->Previous = node;
	node->Next = current;
	node->Previous = previous;

	previous->Unlock();
	current->Unlock();
	this->ReleaseSharedLock();
}

template<class T>
inline 
void 
SorteLinkedListFineGrained<T>::Delete(
	_In_ _Const_ const T & Element
)
{
	this->AcquireSharedLock();

	// We are sure that there are at least 2 nodes (head and tail)
	this->Head->Lock();
	this->Head->Next->Lock();

	Node* previous = this->Head;
	Node* current = this->Head->Next;

	while (current != this->Tail && Element > current->Value)
	{
		current->Next->Lock();
		previous->Unlock();

		previous = current;
		current = current->Next;
	}

	// Element wasn't found in the list
	if (current == this->Tail || Element != current->Value)
	{
		goto Exit;
	}

	current->Next->Lock();
	previous->Next = current->Next;
	current->Next->Previous = previous;
	
	current->Unlock();
	delete current;
	current = previous->Next;

Exit:
	previous->Unlock();
	current->Unlock();
	this->ReleaseSharedLock();
}

#endif //_SORTED_LINKED_LIST_FINE_GRAINED_HPP_