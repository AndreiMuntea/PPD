#include "CppUnitTest.h"

#include "SortedLinkedListFineGrained.hpp"
#include "SortedLinkedListCoarseGrained.hpp"

#include <Windows.h>
#include <fstream>
#include <vector>
#include <string>
#include <ctime>
#include <ratio>
#include <chrono>
#include <random>

using namespace Microsoft::VisualStudio::CppUnitTestFramework;

#define NO_THREADS 4
#define WAIT_THREAD_MILIS 1
#define SLEEP_THREAD_MILIS 15

HANDLE Events[NO_THREADS - 1];
std::ofstream q;
std::mutex FileMutex;
std::random_device rd;
std::mt19937 gen(rd()); 
std::uniform_int_distribution<> dist(-100, 100);


std::vector<double> GetRandomVector(unsigned int NoValues)
{
	std::vector<double> v;
	for (unsigned int i = 0; i < NoValues; ++i)
	{
		int val = dist(gen);
		v.push_back(val);
	}
	return v;
}

void Log(const std::string& Message)
{
	std::chrono::system_clock::time_point today = std::chrono::system_clock::now();
	time_t tt = std::chrono::system_clock::to_time_t(today);

	FileMutex.lock();

	q << ctime(&tt) << Message << "\n";

	FileMutex.unlock();
}

void SetUp(const std::string& FileName)
{
	for (int i = 0; i < NO_THREADS - 1; ++i)
	{
		Events[i] = CreateEvent(nullptr, true, false, nullptr);
	}

	q.open(FileName, std::fstream::app);
}

void TearDown()
{
	for (int i = 0; i < NO_THREADS - 1; ++i)
	{
		CloseHandle(Events[i]);
	}

	q.close();
}

void InsertDoublyLinkedList(DoublyLinkedList<double>& List, const std::vector<double>& Values, int ThreadIndex)
{
	for (const auto& val : Values)
	{
		List.Insert(val);
		Log("[THREAD: " + std::to_string(ThreadIndex) + "]: Insert " + std::to_string(val));
		std::this_thread::sleep_for(std::chrono::milliseconds(SLEEP_THREAD_MILIS));
	}

	SetEvent(Events[ThreadIndex]);
}

void DeleteDoublyLinkedList(DoublyLinkedList<double>& List, const std::vector<double>& Values, int ThreadIndex)
{
	for (const auto& val : Values)
	{
		List.Delete(val);
		Log("[THREAD: " + std::to_string(ThreadIndex) + "]: Delete " + std::to_string(val));
		std::this_thread::sleep_for(std::chrono::milliseconds(SLEEP_THREAD_MILIS));
	}

	SetEvent(Events[ThreadIndex]);
}

void IterateDoublyLinkedList(DoublyLinkedList<double>& List, int ThreadIndex)
{
	while (true)
	{
		std::string val = "";
		auto it = List.GetIterator();
		while (it.IsValid())
		{
			val += std::to_string(it.GetNext()) + " " ;
		}

		Log("[THREAD: " + std::to_string(ThreadIndex) + "]: Iterate " + val);

		auto res = WaitForMultipleObjects(NO_THREADS - 1, Events, true, WAIT_THREAD_MILIS);
		if (res != WAIT_TIMEOUT)
		{
			break;
		}
		std::this_thread::sleep_for(std::chrono::milliseconds(SLEEP_THREAD_MILIS));
	}
}

namespace UnitTest1
{		
	TEST_CLASS(UnitTest1)
	{
	public:
		
		TEST_METHOD(TestMethod1)
		{
			for (int i = 0; i < 10; ++i)
			{

				SetUp("e:\\Projects\\PPD\\PPD\\Lab3\\Lab3\\Log.txt");

				Log("[SortedLinkedListCoarseGrained] Iteration: " + std::to_string(i + 1));
				auto start = std::chrono::high_resolution_clock::now();


				SortedLinkedListCoarseGrained<double> List;
				std::vector<double> Insert1 = { 1, 2, 9, 125, 12, 129, -51, 258.251, 95, -59 };
				std::vector<double> Insert2 = { 3, 19, 45, 22, -54 };
				std::vector<double> Delete1 = { 4, 3, 129, -51, 125 };
				std::vector<std::thread> threads(NO_THREADS);

				threads[0] = std::thread(InsertDoublyLinkedList, std::ref(List), std::ref(Insert1), 0);
				threads[1] = std::thread(InsertDoublyLinkedList, std::ref(List), std::ref(Insert2), 1);
				threads[2] = std::thread(DeleteDoublyLinkedList, std::ref(List), std::ref(Delete1), 2);
				threads[3] = std::thread(IterateDoublyLinkedList, std::ref(List), 3);

				for (int i = 0; i < NO_THREADS; ++i)
				{
					threads[i].join();
				}

				auto end = std::chrono::high_resolution_clock::now();
				Log("Finished in: " + std::to_string(std::chrono::duration<double, std::micro>(end - start).count()));
				Log("------------------------------------------------------------------------------------------------");
				TearDown();
			}
		}

		TEST_METHOD(TestMethod2)
		{
			for (int i = 0; i < 10; ++i)
			{

				SetUp("e:\\Projects\\PPD\\PPD\\Lab3\\Lab3\\Log.txt");

				Log("[SortedLinkedListFineGrained] Iteration: " + std::to_string(i + 1));
				auto start = std::chrono::high_resolution_clock::now();


				SorteLinkedListFineGrained<double> List;
				std::vector<double> Insert1 = { 1, 2, 9, 125, 12, 129, -51, 258.251, 95, -59 };
				std::vector<double> Insert2 = { 3, 19, 45, 22, -54 };
				std::vector<double> Delete1 = { 4, 3, 129, -51, 125 };
				std::vector<std::thread> threads(NO_THREADS);

				threads[0] = std::thread(InsertDoublyLinkedList, std::ref(List), std::ref(Insert1), 0);
				threads[1] = std::thread(InsertDoublyLinkedList, std::ref(List), std::ref(Insert2), 1);
				threads[2] = std::thread(DeleteDoublyLinkedList, std::ref(List), std::ref(Delete1), 2);
				threads[3] = std::thread(IterateDoublyLinkedList, std::ref(List), 3);

				for (int i = 0; i < NO_THREADS; ++i)
				{
					threads[i].join();
				}

				auto end = std::chrono::high_resolution_clock::now();
				Log("Finished in: " + std::to_string(std::chrono::duration<double, std::micro>(end - start).count()));
				Log("------------------------------------------------------------------------------------------------");
				TearDown();
			}
		}





		TEST_METHOD(TestMethod3)
		{
			for (int i = 0; i < 10; ++i)
			{

				SetUp("e:\\Projects\\PPD\\PPD\\Lab3\\Lab3\\Log2.txt");

				Log("[SortedLinkedListCoarseGrained] Iteration: " + std::to_string(i + 1));
				auto start = std::chrono::high_resolution_clock::now();


				SortedLinkedListCoarseGrained<double> List;
				std::vector<double> Insert1 = GetRandomVector(100);
				std::vector<double> Insert2 = GetRandomVector(50);
				std::vector<double> Delete1 = GetRandomVector(50);
				std::vector<std::thread> threads(NO_THREADS);

				threads[0] = std::thread(InsertDoublyLinkedList, std::ref(List), std::ref(Insert1), 0);
				threads[1] = std::thread(InsertDoublyLinkedList, std::ref(List), std::ref(Insert2), 1);
				threads[2] = std::thread(DeleteDoublyLinkedList, std::ref(List), std::ref(Delete1), 2);
				threads[3] = std::thread(IterateDoublyLinkedList, std::ref(List), 3);

				for (int i = 0; i < NO_THREADS; ++i)
				{
					threads[i].join();
				}

				auto end = std::chrono::high_resolution_clock::now();
				Log("Finished in: " + std::to_string(std::chrono::duration<double, std::micro>(end - start).count()));
				Log("------------------------------------------------------------------------------------------------");
				TearDown();
			}
		}

		TEST_METHOD(TestMethod4)
		{
			for (int i = 0; i < 10; ++i)
			{

				SetUp("e:\\Projects\\PPD\\PPD\\Lab3\\Lab3\\Log2.txt");

				Log("[SortedLinkedListFineGrained] Iteration: " + std::to_string(i + 1));
				auto start = std::chrono::high_resolution_clock::now();


				SorteLinkedListFineGrained<double> List;
				std::vector<double> Insert1 = GetRandomVector(100);
				std::vector<double> Insert2 = GetRandomVector(50);
				std::vector<double> Delete1 = GetRandomVector(50);
				std::vector<std::thread> threads(NO_THREADS);

				threads[0] = std::thread(InsertDoublyLinkedList, std::ref(List), std::ref(Insert1), 0);
				threads[1] = std::thread(InsertDoublyLinkedList, std::ref(List), std::ref(Insert2), 1);
				threads[2] = std::thread(DeleteDoublyLinkedList, std::ref(List), std::ref(Delete1), 2);
				threads[3] = std::thread(IterateDoublyLinkedList, std::ref(List), 3);

				for (int i = 0; i < NO_THREADS; ++i)
				{
					threads[i].join();
				}

				auto end = std::chrono::high_resolution_clock::now();
				Log("Finished in: " + std::to_string(std::chrono::duration<double, std::micro>(end - start).count()));
				Log("------------------------------------------------------------------------------------------------");
				TearDown();
			}
		}
	};
}