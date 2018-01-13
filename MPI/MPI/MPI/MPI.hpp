#ifndef _MPI_HPP_
#define _MPI_HPP_

#include <Windows.h>
#include <mpi.h>

#include <iostream>
#include <string>
#include <vector>


class MPI
{
public:
	MPI(
		_In_	int*		Argc,
		_In_	char***		Argv
	);

	~MPI();

	MPI(_In_ _Const_ const MPI& Other) = delete;

	void 
	Send(
		_In_	const int&			SourceRank,
		_In_	const int&			DestinationRank,
		_In_	const std::string&	Message
	);
		
	void 
	Send(
		_In_	const int&			SourceRank,
		_In_	const int&			DestinationRank,
		_In_	const int&			Message
	);

	void 
	Send(
		_In_	const int&				SourceRank,
		_In_	const int&				DestinationRank,
		_In_	const std::vector<int>&	Message
	);

	bool
	Receive(
		_In_	const int&			SourceRank,
		_In_	const int&			DestinationRank,
		_Out_	std::string&		Output
	);

	bool
	Receive(
		_In_	const int&			SourceRank,
		_In_	const int&			DestinationRank,
		_Out_	std::vector<int>&	Output
	);

	bool
	Receive(
		_In_	const int&			SourceRank,
		_In_	const int&			DestinationRank,
		_Out_	int&				Output
	);

	std::string 
	GetProcessorName();

	int
	GetWorld();

	int
	GetRank();

private:
	int tag;
	int rank;
	int world;
};


#endif //_MPI_HPP_