#include <Windows.h>
#include <mpi.h>

#include <iostream>
#include <string>
#include <vector>
#include "MPI.hpp"

int 
GetSum(
	_In_	const std::vector<int>&		Array
);


int
main(
	_In_	int		Argc,
	_In_	char**	Argv
)
{
	{
		MPI mpi{ &Argc, &Argv };

		std::vector<int> arr = { 1,6,5,4,7,9,2,3,8,10 };
		
		for (int i = 0; i < arr.size() % (mpi.GetWorld()-1); ++i)
		{
			arr.push_back(0);
		}
		
		int capacity = static_cast<int>(arr.size()) / (mpi.GetWorld()-1);

		for (int i = 1, start = 0, end = capacity; i <= mpi.GetWorld() - 1; ++i, start = end, end += capacity)
		{
			std::vector<int> v{ arr.begin() + start, arr.begin() + end };
			mpi.Send(0, i, v);
		}

		for (int i = 1; i < mpi.GetWorld(); ++i)
		{
			std::vector<int> r;
			mpi.Receive(0, i, r);
			mpi.Send(i, 0, GetSum(r));
		}

		if (mpi.GetRank() != 0)
		{
			goto CleanUp;
		}

		int s = 0;
		for (int i = 1; i < mpi.GetWorld(); ++i)
		{
			int sum = 0;
			mpi.Receive(MPI_ANY_SOURCE, 0, sum);
			s += sum;
		}

		std::cout << "Suma este: " << s << std::endl;
	}

CleanUp:
	return 0;
}

int
GetSum(
	_In_	const std::vector<int>&		Array
) 
{
	int sum = 0;
	for (const auto& t : Array)
	{
		sum += t;
	}

	return sum;
}