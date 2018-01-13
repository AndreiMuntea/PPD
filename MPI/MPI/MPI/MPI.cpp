#include "MPI.hpp"


#define MAX_NO_ELEMENTS		5000
#define DEFAULT_MPI_TAG		1

MPI::MPI(
	_In_	int*		Argc,
	_In_	char***		Argv
)
{
	MPI_Init(Argc, Argv);
	MPI_Comm_rank(MPI_COMM_WORLD, &this->rank);
	MPI_Comm_size(MPI_COMM_WORLD, &this->world);

	//std::cout << "[LOG]" << "MPI_Comm_rank: " << this->rank << std::endl;
	
	this->tag = DEFAULT_MPI_TAG;
}

MPI::~MPI()
{
	//std::cout << "[LOG]" << "MPI_Finalize " << std::endl;

	MPI_Finalize();
}

void 
MPI::Send(
	_In_	const int&			SourceRank,
	_In_	const int&			DestinationRank,
	_In_	const std::string&	Message
)
{
	if (this->rank != SourceRank)
	{
		return;
	}

	int status = MPI_Send(
		Message.c_str(), 
		static_cast<int>(Message.size()), 
		MPI_CHAR, 
		DestinationRank, 
		this->tag, 
		MPI_COMM_WORLD
	);

	if (status != 0)
	{
		std::cout << "[LOG]" << "MPI_Send failed with status: " << status << std::endl;
	}
}

void
MPI::Send(
	_In_	const int&				SourceRank,
	_In_	const int&				DestinationRank,
	_In_	const std::vector<int>&	Message
)
{
	if (this->rank != SourceRank)
	{
		return;
	}

	this->Send(SourceRank, DestinationRank, static_cast<int>(Message.size()));

	int status = MPI_Send(
		Message.data(),
		static_cast<int>(Message.size()),
		MPI_INT,
		DestinationRank,
		this->tag,
		MPI_COMM_WORLD
	);

	if (status != 0)
	{
		std::cout << "[LOG]" << "MPI_Send failed with status: " << status << std::endl;
	}
}


void
MPI::Send(
	_In_	const int&			SourceRank,
	_In_	const int&			DestinationRank,
	_In_	const int&			Message
)
{
	if (this->rank != SourceRank)
	{
		return;
	}

	int msg = Message;

	int status = MPI_Send(
		&msg,
		1,
		MPI_INT,
		DestinationRank,
		this->tag,
		MPI_COMM_WORLD
	);

	if (status != 0)
	{
		std::cout << "[LOG]" << "MPI_Send failed with status: " << status << std::endl;
	}
}

bool
MPI::Receive(
	_In_	const int&			SourceRank,
	_In_	const int&			DestinationRank,
	_Out_	std::string&		Output
)
{
	Output = "";
	
	if (DestinationRank != this->rank && DestinationRank != MPI_ANY_SOURCE)
	{
		return false;
	}

	char buffer[MAX_NO_ELEMENTS] = { 0 };

	int status = MPI_Recv(
		buffer, 
		MAX_NO_ELEMENTS,
		MPI_CHAR, 
		SourceRank, 
		this->tag, 
		MPI_COMM_WORLD, 
		MPI_STATUS_IGNORE
	);

	if (status != 0)
	{
		std::cout << "[LOG]" << "MPI_Recv failed with status: " << status << std::endl;
	}

	Output = std::string{ buffer };
	return true;
}

bool
MPI::Receive(
	_In_	const int&			SourceRank,
	_In_	const int&			DestinationRank,
	_Out_	std::vector<int>&	Output
)
{
	if (DestinationRank != this->rank && DestinationRank != MPI_ANY_SOURCE)
	{
		return false;
	}
	
	int buffer[MAX_NO_ELEMENTS] = { 0 };
	int count = 0;

	this->Receive(SourceRank, DestinationRank, count);

	int status = MPI_Recv(
		buffer,
		MAX_NO_ELEMENTS,
		MPI_INT,
		SourceRank,
		this->tag,
		MPI_COMM_WORLD,
		MPI_STATUS_IGNORE
	);

	if (status != 0)
	{
		std::cout << "[LOG]" << "MPI_Recv failed with status: " << status << std::endl;
	}

	for (int i = 0; i < count; ++i)
	{
		Output.push_back(buffer[i]);
	}

	return true;
}

bool
MPI::Receive(
	_In_	const int&			SourceRank,
	_In_	const int&			DestinationRank,
	_Out_	int&				Output
)
{
	Output = 0;

	if (DestinationRank != this->rank && DestinationRank != MPI_ANY_SOURCE)
	{
		return false;
	}

	int out = 0;

	int status = MPI_Recv(
		&out,
		1,
		MPI_INT,
		SourceRank,
		this->tag,
		MPI_COMM_WORLD,
		MPI_STATUS_IGNORE
	);

	if (status != 0)
	{
		std::cout << "[LOG]" << "MPI_Recv failed with status: " << status << std::endl;
	}

	Output = out;

	return true;
}


std::string 
MPI::GetProcessorName()
{
	char buffer[MPI_MAX_PROCESSOR_NAME + 1] = { 0 };
	int len = 0;

	MPI_Get_processor_name(buffer, &len);

	return std::string{ buffer } + " " + std::to_string(this->rank);
}

int
MPI::GetWorld()
{
	return this->world;
}

int
MPI::GetRank()
{
	return this->rank;
}
