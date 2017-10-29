// Lab3.cpp : Defines the entry point for the console application.
//

#include "SortedLinkedListCoarseGrained.hpp"
#include <iostream>

#define _CRTDBG_MAP_ALLOC  
#include <stdlib.h>  
#include <crtdbg.h>  

void Print(
	_In_ SortedLinkedListCoarseGrained<int>& List
)
{
	auto iterator = List.GetIterator();

	while (iterator.IsValid())
	{
		std::cout << iterator.GetNext() << " ";
	}

	std::cout << "\n";
}

int main()
{
	{
		SortedLinkedListCoarseGrained<int> l;

		Print(l);

		l.Insert(10);
		Print(l);

		l.Insert(8);
		Print(l);

		l.Delete(11);
		Print(l);
		
		l.Insert(11);
		Print(l);

		l.Insert(9);
		Print(l);

		l.Delete(10);
		Print(l);

		l.Delete(8);
		Print(l);

	}
	_CrtDumpMemoryLeaks();

}

