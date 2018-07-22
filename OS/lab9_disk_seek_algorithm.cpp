#include<bits/stdc++.h>
#include<unistd.h>

#define LEN 65536
using namespace std;

int s, m, n;
int arr[LEN];
int arr_copy[LEN];

void read_input() {
  cin >> s >> m >> n;
  int temp;
  for (int i = 0; i < n; i++) {
    cin >> temp; 
    arr[i] = temp;
  }
  copy(begin(arr), end(arr), begin(arr_copy));
  sort(arr_copy, arr_copy + n);
}

void fcfs() {
  int result = 0;
  int current = s;
  int next;
  cout << "FCFS" << endl;
  for (int i = 0; i < n; i++) {
    next = arr[i];
    cout << current << " -> ";
    if (i == n - 1) {
      cout << next << endl;
    } 

    if (current > next) {
      result += (current - next);
    } else {
      result += (next - current);
    } 
    current = next;

  }
  cout << result << endl;

}

int find_index(int elem, int *sorted_arr) {
  for (int i = 0; i < n; i++) {
    if (sorted_arr[i] > elem) {
      return i;
    }
  }
  return n;
}

void sstf() {
  int flags[n];
  int index, r_index, l_index;
  int result = 0;
  int times = 0;
  int current = s;
  int next;
  memset(flags, 0, sizeof(flags));
  /**for (int i = 0; i < n; i++ ) {
    cout << arr_copy[i] << " ";
  }
  cout << endl;***/
  
  index = find_index(current, arr_copy);
  
  cout << "SSTF" << endl;
  while (times < n) {
    cout << current << " -> "; 
    r_index = index; 
    l_index = r_index - 1;
    while (flags[r_index] && r_index < n) {
      r_index++;
    }
    while (flags[l_index] && l_index >= 0) {
      l_index--;
    }
    if (r_index == n) {
      index = l_index;
    } else if (l_index == -1) {
      index = r_index;
    } else {
    if (abs(current - arr_copy[l_index]) < 
            abs(current - arr_copy[r_index])) {
      index = l_index;
    } else {
      index = r_index;
    }
    }
    next = arr_copy[index];
    flags[index] = 1;
    result += abs(current - next);
    times++;
    current = next;
    if (times == n) {
      cout << current <<endl;
    }
  }
  cout << result << endl;
}

void scan() {
  int current = s;
  int l_index, r_index;
  int result = 0;
  int index = find_index(current, arr_copy);
  r_index = index;
  l_index = index - 1;

  cout << "SCAN" << endl;
  cout << current << " -> ";

    result = s + arr_copy[n - 1];
    while (l_index >= 0) {
      current = arr_copy[l_index];
      cout << current << " -> ";
      l_index--;
    } 
    cout << "0" << " -> "; 
    while (r_index < n) {
      current = arr_copy[r_index];
      if (r_index == n - 1) {
        cout << current << endl;
      }
      else
        cout << current << " -> ";
      r_index++;
    } 
     cout << result << endl;

}

void cscan() {
  int current = s;
  int l_index, r_index;
  int result = 0;
  int index = find_index(current, arr_copy);
  r_index = index;
  l_index = index - 1;
  int i = n - 1;

  cout << "C-SCAN" << endl;
  cout << current << " -> ";

    result = s + m - 1 - arr_copy[index];
    while (l_index >= 0) {
      current = arr_copy[l_index];
      cout << current << " -> ";
      l_index--;
    } 
    cout << "0" << " -> "; 
    cout << m - 1 << " -> ";
    i = n - 1;
    while (r_index <= i) {
      current = arr_copy[i];
      if (r_index == i) {
        cout << current << endl;
      }
      else
        cout << current << " -> ";
      i--;
    } 

  cout << result << endl;
}

void look() {
  int current = s;
  int l_index, r_index;
  int result = 0;
  int index = find_index(current, arr_copy);
  r_index = index;
  l_index = index - 1;

  cout << "LOOK" << endl;
  cout << current << " -> ";

    result = s + arr_copy[n - 1] - 2 * arr_copy[0];
    while (l_index >= 0) {
      current = arr_copy[l_index];
      cout << current << " -> ";
      l_index--;
    } 
    while (r_index < n) {
      current = arr_copy[r_index];
      if (r_index == n - 1) {
        cout << current << endl;
      }
      else
        cout << current << " -> ";
      r_index++;
    } 

    cout << result << endl;

}

void clook() {
  int current = s;
  int l_index, r_index;
  int result = 0;
  int index = find_index(current, arr_copy);
  r_index = index;
  l_index = index - 1;
  int i = n - 1;

  cout << "C-LOOK" << endl;
  cout << current << " -> ";

    result = s - arr_copy[0] + arr_copy[n-1] - arr_copy[r_index];
    while (l_index >= 0) {
      current = arr_copy[l_index];
      cout << current << " -> ";
      l_index--;
    } 
    i = n - 1;
    while (r_index <= i) {
      current = arr_copy[i];
      if (r_index == i) {
        cout << current << endl;
      }
      else
        cout << current << " -> ";
      i--;
    } 

   cout << result << endl;
}



int main(int argc, char **argv) {
  read_input();
  fcfs();  
  sstf();
  scan();
  cscan();
  look();
  clook();
  return 0;

}


