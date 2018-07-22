#include<bits/stdc++.h>
#include<unistd.h>

using namespace std;

const double eps = 1e-8;
int dcmp(const double &x){ if (fabs(x) < eps) return 0; return x < 0 ? -1 : 1; }

#define DEFAULT_CACHE_SIZE 123
#define DEFAULT_ALGORITHM 4

typedef pair<int, string> _algorithm;

int cache_size, working_algorithm, hit, miss;
vector<_algorithm> algorithms;
vector<int> pages;

struct Node {
 int page;
 int valid;
};

void init();
void print_menu();
void set_cache_size();
void print_algorithms();
void set_algorithm();
void add_an_algorithm();
void generate_input();
void generate_randomly(int n);
void generate_by_hand(int n);
void print_pages();
void FIFO_algorithm();
void run_algorithm();
void read_pages();
void LRU_algorithm();
void MIN_algorithm();
void CLOCK_algorithm();
void SS_algorithm();
int find_least_page(list<int> &cache_list, vector<int> &pages, int from);

int main(){

    init();

    //int op;
    //print_menu();
    /****
    while (cin >> op){
        switch (op){
            case 1: set_cache_size(); break;
            case 2: set_algorithm(); break;
            case 3: add_an_algorithm(); break;
            case 4: run_algorithm(); break;
            case 5: read_pages(); break;
            case 233: cout << "Bye..." << endl; return 0;
            default: break;
        }
        //sleep(1);
        print_menu();
    }***/
    set_cache_size();
    set_algorithm();
    read_pages();
    run_algorithm();
}

void print_pages(){
    cout << "pages number is: ";
    cout << pages.size() << endl;
    cout << "pages are: ";
    for (auto &x : pages){
        cout << x << " ";
    }
    cout << endl;
}

void generate_randomly(int n){
    cout << n << endl;
    for (int i = 0; i < n; i++){
        cout << rand() * 233 % cache_size << " ";
    }
    cout << endl;
}

void generate_by_hand(int n){
    cout << n << endl;
    for (int i = 0; i < n; i++){
        int x; cin >> x; cout << x << " ";
    }
    cout << endl;
}

void generate_input(){
    pages.clear();
    cout << "Please input the number of pages: ";
    int n, op;
    cin >> n;
    cout << "1) Generate randomly" << endl;
    cout << "2) Generate by hand" << endl;
    cin >> op;
    freopen("1.in", "w", stdout);
    switch (op){
        case 1: generate_randomly(n); break;
        case 2: generate_by_hand(n); break;
        default: break;
    }
}

void init(){
    srand((unsigned int)time(0));
    algorithms.clear(); pages.clear();
    //cout << "Setting the default cache size..." << endl;
    cache_size = DEFAULT_CACHE_SIZE;
    //sleep(1);
    //cout << "Setting the default algorithm..." << endl;
    string s = "FIFO";
    algorithms.push_back(make_pair(DEFAULT_ALGORITHM, s));
    s = "LRU";
    algorithms.push_back(make_pair(DEFAULT_ALGORITHM + 1, s));
    s = "MIN";
    algorithms.push_back(make_pair(DEFAULT_ALGORITHM + 2, s));
    s = "CLOCK";
    algorithms.push_back(make_pair(DEFAULT_ALGORITHM + 3, s));
    s = "SS";
    algorithms.push_back(make_pair(DEFAULT_ALGORITHM + 4, s));

    working_algorithm = DEFAULT_ALGORITHM;
    //sleep(1);
    //cout << "Setting the environment..." << endl;
    //sleep(1);
    //cout << "Setting successfully" << endl;
}

void read_pages(){
    pages.clear();
    int n; cin >> n;
    for (int i = 0; i < n; i++){
        int x; cin >> x; pages.push_back(x);
    }
    //cout << "Read successfully" << endl;

}

void print_menu(){
    cout << "**********MENU**********" << endl;
    cout << "1) set cache size" << endl;
    cout << "2) set algorithm" << endl;
    cout << "3) add an algorithm" << endl;
    cout << "4) run algorithm" << endl;
    cout << "5) read pages" << endl;
    cout << "233) quit" << endl;
}

void set_cache_size(){
    //cout << "Please input the new cache size: ";
    int x;
    cin >> x;
    if (x <= 0){
        //cerr << "Invalid cache size" << endl;
        return;
    }
    cache_size = x;
    //cout << "set cache size successfully, new cache size is: " << cache_size << endl;
}

void print_algorithms(){
    cout << "All algorithms are: " << endl;
    for (auto &x : algorithms){
        cout << x.first << " : " << x.second << endl;
    }
}

void set_algorithm(){
    /***print_algorithms();
    cout << "Please input the index of algorithm" << endl;**/
    unsigned int id;
    cin >> id;
    working_algorithm = id;
    if (id >= algorithms.size()){
        //cerr << "Algorithm does not exists" << endl;
        return;
    }
    /***cout << "Set algorithm successfully" << endl;
    cout << "Now working algorithm is: " << algorithms[id].first << ":" << algorithms[id].second << endl;***/
}

void add_an_algorithm(){
    cout << "Please input the name of new algorithm: ";
    string algo;
    cin >> algo;
    algorithms.push_back(make_pair(algorithms.size(), algo));
    cout << "Algorithm added!" << endl;
    print_algorithms();
}

int find_least_page(list<int> &cache_list, vector<int> &pages, int from) {
  int index = -1;
  int result = -1;
  for (auto &cache: cache_list) {
    int i;
    for (i = from; i < (int)pages.size(); i++) 
    {
      if (pages[i] == cache) {
        if (i > index) {
           index = i;
        } 
        break;
      }
    } 
    if (i == (int) pages.size())
      result = cache;
  }
  if (result != -1)
    return result;
  else 
    return pages[index];

}
void FIFO_algorithm(){
  hit = 0;
  miss = 0;
  list<int> cache_list; 

  for (auto &page: pages) {
   if (cache_list.empty()) {
     miss++;
     cache_list.push_back(page);
     continue;
   }

   bool is_hit = false;
    for (auto &cache : cache_list) {
      if (page == cache) {
        hit++;
        is_hit = true;
        break;
      }
    }
    if (!is_hit) {
      miss++;
      if ((int)cache_list.size() == cache_size) {
        cache_list.pop_front();
      }
      cache_list.push_back(page);
    }
  
  }

}


void LRU_algorithm(){
  hit = 0;
  miss = 0;
  list<int> cache_list;

  for (auto &page : pages){
    if (cache_list.empty()) {
      miss++;
      cache_list.push_back(page);
      continue;
    }

    bool is_hit = false;
    for (auto &cache : cache_list) {
      if (page == cache) {
        hit++;
        is_hit = true;
        cache_list.remove(cache);
        cache_list.push_back(page);
        break;
      }
    }
    if (!is_hit) {
      miss++;
      if ((int)cache_list.size() == cache_size) {
        cache_list.pop_front();
      }
      cache_list.push_back(page);
    }
  }


}

void MIN_algorithm() {
  hit = 0;
  miss = 0;
  list<int> cache_list;

  for (int i = 0; i < (int)pages.size(); i++) {
    if (cache_list.empty()) {
      miss++;
      cache_list.push_back(pages[i]);
      continue;
    }

    bool is_hit = false;
    for (auto &cache : cache_list) {
      if (pages[i] == cache) {
        hit++;
        is_hit = true;
        break;
      }
    }
    if (!is_hit) {
      miss++;
      if ((int)cache_list.size() == cache_size) {
        int to_remove = find_least_page(cache_list, pages, i + 1);
        cache_list.remove(to_remove);
      }
      cache_list.push_back(pages[i]);
    }
  }
}

void CLOCK_algorithm() {
  hit = 0;
  miss = 0;

  vector<Node> caches;
  caches.reserve(1000000);
  int hand = 0;

  for (int i = 0; i < (int)pages.size(); i++) {
    if (caches.empty()) {
      miss++;
      caches.push_back((Node){pages[i], 1});
      hand = (hand + 1) % cache_size;
      continue;
    }

    bool is_hit = false;
    for (auto &cache : caches) {
      if (pages[i] == cache.page) {
        hit++;
        is_hit = true;
        cache.valid = 1;
        break;
      }
    }
    if (!is_hit) {
      miss++;
      if ((int)caches.size() == cache_size) {
        while(caches[hand].valid == 1) {
          caches[hand].valid = 0;
          hand = (hand + 1) % cache_size;
        }
        caches[hand] = (Node){pages[i], 1};
      } else {
        caches.push_back((Node){pages[i], 1});
      }
      hand = (hand + 1) % cache_size;
    }
  }


}

void SS_algorithm() {
  hit = 0;
  miss = 0;
  int fifo_size = cache_size / 2;
  int lru_size = cache_size - fifo_size;

  list<int> fifo_list;
  list<int> lru_list;

  for (auto &page : pages){
    if (fifo_list.empty()) {
      miss++;
      fifo_list.push_back(page);
      continue;
    }

    bool is_hit = false;
    for (auto &cache : fifo_list) {
      if (page == cache) {
        hit++;
        is_hit = true;
        break;
      }
    }
    
    if (!is_hit && lru_list.size() != 0) {
        for (auto &cache: lru_list) {
          if (page == cache) {
            hit++;
            is_hit = true;
            lru_list.remove(cache);
            lru_list.push_back(fifo_list.front());
            fifo_list.pop_front();
            fifo_list.push_back(cache);
            break;
          }
        }
    }

    if (!is_hit) {
      miss++;
      if ((int)fifo_list.size() == fifo_size) {
        lru_list.push_back(fifo_list.front());
        fifo_list.pop_front();
        if (lru_list.size() > lru_size) {
          lru_list.pop_front();
        }
      }
      fifo_list.push_back(page);
    }
  }


}



void print_status(){
    /***cout << "cache size is: " << cache_size << endl;
    cout << "working algorithm is " << working_algorithm << ":" << algorithms[working_algorithm].second << endl;
    cout << "hit times = " << hit << endl << "miss times = " << miss << endl;***/
    cout << "Hit ratio = " << fixed << setprecision (2) << (double)hit * 100 / (miss + hit) << "%" << endl;
}

void run_algorithm(){
    switch (working_algorithm){
        case 0: FIFO_algorithm(); break;
        case 1: LRU_algorithm(); break;
        case 2: MIN_algorithm(); break;
        case 3: CLOCK_algorithm(); break;
        case 4: SS_algorithm(); break;
        default: break;
    }
    print_status();
}
