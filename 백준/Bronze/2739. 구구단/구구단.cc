# include <iostream>
using namespace std;

int main() {
	int N;
	cin >> N;
	for (int i = 1; i < 10; i++) {
		int total = N * i;
		cout << N << " * " << i << " = " << total << endl;
		total = 0;
	}
	return 0;
};
	
