#ifndef _SORTED_LINKED_LIST_COARSE_GRAINED_HPP_
#define _SORTED_LINKED_LIST_COARSE_GRAINED_HPP_

#include "DoublyLinkedList.hpp"

template <class T>
class SortedLinkedListCoarseGrained : public DoublyLinkedList<T>
{
public:
	SortedLinkedListCoarseGrained() = default;

	virtual 
	~SortedLinkedListCoarseGrained() = default;

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


#endif //_SORTED_LINKED_LIST_COARSE_GRAINED_HPP_

template<class T>
inline 
void 
SortedLinkedListCoarseGrained<T>::Insert(
	_In_ _Const_ const T & Element
)
{
	this->AcquireExclusiveLock();

	Node* previous = this->Head;
	Node* current = this->Head->Next;
	Node* node = new Node(Element);

	while (current != this->Tail && Element > current->Value)
	{
		previous = current;
		current = current->Next;
	}

	previous->Next = node;
	current->Previous = node;
	node->Next = current;
	node->Previous = previous;

	this->ReleaseExclusiveLock();
}

template<class T>
inline 
void 
SortedLinkedListCoarseGrained<T>::Delete(
	_In_ _Const_ const T & Element
)
{
	this->AcquireExclusiveLock();

	Node* previous = this->Head;
	Node* current = this->Head->Next;

	while (current != this->Tail && Element > current->Value)
	{
		previous = current;
		current = current->Next;
	}

	// Element wasn't found in the list
	if (current == this->Tail || Element != current->Value)
	{
		goto Exit;
	}

	previous->Next = current->Next;
	current->Next->Previous = previous;
	delete current;

Exit:
	this->ReleaseExclusiveLock();
}_