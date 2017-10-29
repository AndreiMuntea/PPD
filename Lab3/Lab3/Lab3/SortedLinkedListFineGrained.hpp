#ifndef _SORTED_LINKED_LIST_FINE_GRAINED_HPP_
#define _SORTED_LINKED_LIST_FINE_GRAINED_HPP_

#include <mutex>
#include <sal.h>

template <class T>
class SorteLinkedListFineGrained
{
private:
	class Node
	{
	public:
		Node* next = nullptr;
		T value;
		std::mutex guard;

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
};

#endif //_SORTED_LINKED_LIST_FINE_GRAINED_HPP_

template<class T>
inline
SorteLinkedListFineGrained<T>::Node::Node(
	_In_ const T & Value
) :
	next{ nullptr },
	value{ Value },
	guard{}
{}