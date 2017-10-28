#ifndef WORKERS_HPP
#define WORKERS_HPP

#include <functional>
#include <thread>
#include <chrono>
#include <sal.h>
#include "MatrixOperator.hpp"

template <class T>
double DoWork(
	_In_ Matrix<T>& FirstMatrix,
	_In_ Matrix<T>& SecondMatrix,
	_Inout_ Matrix<T>& Result,
	_In_ size_t NumberOfThreads,
	_In_ std::function<void(Matrix<T>&, Matrix<T>&, Matrix<T>&, size_t, size_t)> Function
)
{
	size_t quotient = FirstMatrix.GetNoLines() / NumberOfThreads;
	size_t remainder = FirstMatrix.GetNoLines() % NumberOfThreads;

	std::vector<std::thread> threads(NumberOfThreads);

	auto startTicking = std::chrono::high_resolution_clock::now();

	// Create threads
	for (size_t i = 0, start = 0, end = quotient; i < NumberOfThreads; ++i, start = end, end += quotient)
	{
		if (i < remainder)
		{
			++end;
		}

		threads[i] = std::thread(
			Function,
			std::ref(FirstMatrix),
			std::ref(SecondMatrix),
			std::ref(Result),
			start,
			end
		);
	}

	// Wait Threads
	for (size_t i = 0; i < NumberOfThreads; ++i)
	{
		threads[i].join();
	}

	auto duration = std::chrono::high_resolution_clock::now() - startTicking;
	return std::chrono::duration<double, std::milli>(duration).count();
}

#endif //WORKERS_HPP