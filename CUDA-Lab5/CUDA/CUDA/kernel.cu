
#include "cuda_runtime.h"
#include "device_launch_parameters.h"

#include <sal.h>
#include <malloc.h>
#include <stdio.h>


#define UNUSED_PARAMTER(x) ((void)(x))
#define MAX(a, b) (((a) > (b)) ? (a) : (b))


__device__
unsigned int
GetNumberOfDigits(
	_In_	__int64		Value
)
{
	if (Value == 0)
	{
		return 1;
	}

	int result = 0;
	for (; Value != 0; ++result, Value /= 10);

	return result;
}


__device__
__int64
Pow(
	_In_	__int64		Base,
	_In_	__int64		Power
)
{
	if (Power == 0)
	{
		return 1;
	}

	if (Power == 1)
	{
		return Base;
	}

	__int64 result = Pow(Base * Base, Power / 2);
	if (Power % 2 != 0)
	{
		result *= Base;
	}

	return result;
}


__global__
void
Karatsuba(
	_In_   __int64		FirstNumber,
	_In_   __int64		SecondNumber,
	_Out_  __int64*		Result
)
{
	// Base case
	if (FirstNumber < 10 || SecondNumber < 10)
	{
		*Result =  FirstNumber * SecondNumber;
		return;
	}

	int xLength = GetNumberOfDigits(FirstNumber);
	int yLength = GetNumberOfDigits(SecondNumber);
	int maxLength = MAX(xLength, yLength);

	int N = (maxLength / 2) + (maxLength % 2);

	__int64 multiplier = Pow(10, N);

	__int64 b = FirstNumber / multiplier;				// high part of the first number
    __int64 a = FirstNumber - (b * multiplier);			// low part of the first number

	__int64 d = SecondNumber / multiplier;				// high part of the second number
	__int64 c = SecondNumber - (d * multiplier);		// low part of the second number

	__int64* result1 = (__int64*)malloc(sizeof(__int64));
	__int64* result2 = (__int64*)malloc(sizeof(__int64));
	__int64* result3 = (__int64*)malloc(sizeof(__int64));

	Karatsuba <<<1, 1 >>> (a, c, result1);
	Karatsuba <<<1, 1 >>> (a + b, c + d, result2);
	Karatsuba <<<1, 1 >>> (b, d, result3);

	cudaDeviceSynchronize();

	*Result =  *result1 + ((*result2 - *result1 - *result3) * multiplier) + (*result3 * Pow(10, 2 * N));

	free(result1);
	free(result2);
	free(result3);
}


cudaError_t
Solve(
	_In_   __int64	 FirstNumber,
	_In_   __int64	 SecondNumber,
	_Out_  __int64*  Result
);

int
main(
	_In_	int		ArgumentsCount,
	_In_	char*	Arguments[]
)
{
	UNUSED_PARAMTER(ArgumentsCount);
	UNUSED_PARAMTER(Arguments);

	__int64 a = 100001;
	__int64 b = 100001;
	__int64 c = 0;

	cudaError_t cudaStatus = Solve(a, b, &c);
    if (cudaStatus != cudaSuccess) {
        fprintf(stderr, "Solve failed!");
        return 1;
    }

	fprintf(stdout, "Result of %lld * %lld = %lld\n", a, b, c);

    // cudaDeviceReset must be called before exiting in order for profiling and
    // tracing tools such as Nsight and Visual Profiler to show complete traces.
    cudaStatus = cudaDeviceReset();
    if (cudaStatus != cudaSuccess) {
        fprintf(stderr, "cudaDeviceReset failed!");
        return 1;
    }

    return 0;
}

cudaError_t
Solve(
	_In_   __int64	 FirstNumber,
	_In_   __int64	 SecondNumber,
	_Out_  __int64*  Result
)
{
	__int64* dev_c = 0;
	cudaError_t cudaStatus = cudaSuccess;

	cudaStatus = cudaMalloc((void**)&dev_c, sizeof(__int64));
	if (cudaStatus != cudaSuccess) {
		fprintf(stderr, "cudaMalloc failed!");
		goto Error;
	}

    cudaStatus = cudaDeviceSetLimit(cudaLimitMallocHeapSize, 1024 * 1024 * 500);
	if (cudaStatus != cudaSuccess) {
		fprintf(stderr, "cudaDeviceSetLimit failed!");
		goto Error;
	}

	Karatsuba<<<1, 1>>>(FirstNumber, SecondNumber, dev_c);

	// Check for any errors launching the kernel
	cudaStatus = cudaGetLastError();
	if (cudaStatus != cudaSuccess) {
		fprintf(stderr, "Kernel launch failed: %s\n", cudaGetErrorString(cudaStatus));
		goto Error;
	}

	// cudaDeviceSynchronize waits for the kernel to finish, and returns
	// any errors encountered during the launch.
	cudaStatus = cudaDeviceSynchronize();
	if (cudaStatus != cudaSuccess) {
		fprintf(stderr, "cudaDeviceSynchronize returned error code %d after launching addKernel!\n", cudaStatus);
		goto Error;
	}


	cudaStatus = cudaMemcpy(Result, dev_c, sizeof(__int64), cudaMemcpyDeviceToHost);
	if (cudaStatus != cudaSuccess) {
		fprintf(stderr, "cudaMemcpy failed!");
		goto Error;
	}

Error:
	cudaFree(dev_c);

	return cudaStatus;
}