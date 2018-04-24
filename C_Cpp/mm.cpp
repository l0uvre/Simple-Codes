/***
 *A program to simulate best fit and worse fit algorithm in memory management.
 *
 *
 * **/



#include<bits/stdc++.h>
#include<unistd.h>

using namespace std;

#define PROCESS_NAME_LEN 32 //进程名最大长度
#define MIN_SLICE 10 //内碎片最大大小
#define DEFAULT_MEM_SIZE 1024  //总内存大小
#define DEFAULT_MEM_START 0  //内存开始分配时的起始地址

typedef pair<int, string> My_algo;

int mem_size = DEFAULT_MEM_SIZE;
bool flag = 0; //当内存以及被分配了之后，不允许更改总内存大小的flag
static int pid = 0;
//My_algo algo[123];
My_algo algo[3] = {make_pair(0, "Best Fit"), make_pair(1, "Worst Fit"), make_pair(2, "First Fit")};


//algo[0] = make_pair(0, "Best Fit");
//algo[1] = make_pair(1, "Worst Fit");

My_algo actual_algo = algo[0];

struct free_block{	//空闲数据块
	int size;
	int start_addr;
	struct free_block *next;
};

struct allocated_block{ //已分配的数据块
	int pid;
	int size;
	int start_addr;
	char process_name[PROCESS_NAME_LEN];
	int *data;
	struct allocated_block *next;
};

free_block *free_block_head; //空闲数据块首指针
allocated_block *allocated_block_head = NULL; //分配块首指针

allocated_block *find_process(int id); //寻找pid为id的分配块
free_block *init_free_block(int mem_size); //空闲块初始化
void display_menu(); //显示选项菜单
void set_mem_size(); //设置内存大小
int allocate_mem(allocated_block *ab); //为制定块分配内存
void rearrange(); // 对块进行重新分配
void rearrange1(); // 对块进行重新分配
void rearrange2(); // 对块进行重新分配
void choose_algo();
int create_new_process(); //创建新的进程
int free_mem(allocated_block *ab); //释放分配块
void swap(int *p, int *q); //交换地址
int dispose(allocated_block *ab); //释放分配块结构体
void display_mem_usage(); //显示内存情况
void kill_process(); //杀死对应进程并释放其空间与结构体
void Usemy_algo(int id); //使用对应的分配算法

//主函数
int main(){
	int op;
	pid = 0;
	free_block_head = init_free_block(mem_size); //初始化一个可以使用的内存块，类似与操作系统可用的总存储空间
	for(;;){
		sleep(1);
		display_menu();
		fflush(stdin);
		scanf("%d", &op);
		switch (op){
			case 1:{ set_mem_size(); break; }
			case 2:{ choose_algo(); break; }
			case 3:{ create_new_process(); break; }
			case 4:{ kill_process(); break; }
			case 5:{ display_mem_usage(); break; }
			case 233:{ puts("bye...."); sleep(1); return 0; }
			defaut: break;
		}
	}
}

void choose_algo() {
  int opt;
  printf("please choose the algorithm. 0 for Best Fit; 1 for Worst Fit.\n");
  scanf("%d", &opt);
  if (opt == 0) {
    actual_algo = algo[0];
  } else if(opt == 1) {
    actual_algo = algo[1]; 
  }

}

allocated_block *find_process(int id){ //循环遍历分配块链表，寻找pid=id的进程所对应的块
  allocated_block *ab = allocated_block_head;
  while (ab && ab->pid != id) {
    ab = ab->next;
  }
  return ab;
}

free_block *init_free_block(int mem_size){ //初始化空闲块，这里的mem_size表示允许的最大虚拟内存大小
	free_block *p;
	p = (free_block *)malloc(sizeof(free_block));
	if (p == NULL){
		puts("No memory left");
		return NULL;
	}
	p->size = mem_size;
	p->start_addr = DEFAULT_MEM_START;
	p->next = NULL;
	return p;
}

void display_menu(){
	puts("\n\n******************menu*******************");
	printf("1) Set memory size (default = %d)\n", DEFAULT_MEM_SIZE);
	printf("2) Set memory allocation algorithm\n");
	printf("3) Create a new process\n");
	printf("4) Kill a process\n");
	printf("5) Display memory usage\n");
	printf("233) Exit\n");
}

void set_mem_size(){ //更改最大内存大小
 if (flag || allocated_block_head) {
   printf("sorry, the memory size couldn't be changed once again.\n");
   return;
 } 
  puts("Please input the mem size you want to change: ");
  scanf("%d", &mem_size);
  free_block_head->size = mem_size;
  puts("Memory resized.");
  flag = 1;
}

int allocate_mem(allocated_block *ab){ //为块分配内存，真正的操作系统会在这里进行置换等操作
  if (!allocated_block_head)
  {
    if (ab->size > mem_size) {
        free(ab);
        fprintf(stderr, "Sorry, the size you required is too big");
        return -1;
    }
    allocated_block_head = ab; 
    ab->start_addr = DEFAULT_MEM_START;
    free_block_head->start_addr = ab->size;
    free_block_head->size = mem_size - ab->size;
    return 1;
  } else {
//    allocated_block *curr = allocated_block_head;
    free_block *fb = free_block_head; 
    free_block *prev = NULL;
    //free_block *prev = free_block_head;
    if (actual_algo.second == "Best Fit") {
       rearrange1(); 
    } else if (actual_algo.second == "Worst Fit") {
       rearrange2();
    }
       while (fb) {
        if (fb->size > ab->size) {
          break;
        }
        prev = fb;
        fb = fb->next;
       }       
       if (!fb) { 
        free(ab);
        fprintf(stderr, "Sorry, the size you required is too big");
        return -1;
       }

       ab->start_addr = fb->start_addr;
       if (prev) {
         free_block *p = (free_block*) malloc(sizeof(free_block));
         p->size = fb->size - ab->size;
         p->start_addr = fb->start_addr + ab->size;
         prev->next = p;
         p->next = fb->next;
         free(fb);
       } else {
         fb->start_addr = fb->start_addr + ab->size;
         fb->size -= ab->size;
       }
    
    ab->next = allocated_block_head;
    allocated_block_head = ab;
    return 0; 
  }
}

int create_new_process(){ //创建新进程
//it will pop user to set a size for this process.
  int block_size = 0; 
  puts("Please set the size for this process: ");
  while (block_size <= 0) {
    scanf("%d", &block_size);
    if (block_size < 0) {
        printf("Sorry, please input a positive number.");
    }
  }
  allocated_block *ab = (allocated_block *)malloc(sizeof(allocated_block));
  if (!ab) {
   fprintf(stderr, "Sorry, really Mem run out.");
   return -1;
  } 

  ab->size = block_size;
  if (allocate_mem(ab) >= 0) {
    pid++;
    ab->pid = pid;
    printf("Process %d with %d sizes has been created.", pid, block_size);
  }

  return 0;
}

void swap(int *p, int *q){
	int tmp = *p;
	*p = *q;
	*q = tmp;
	return;
}

void rearrange(){ //将块按照地址大小进行排序
	free_block *tmp, *tmpx;
	puts("Rearrange begins...");
	puts("Rearrange by address...");
	tmp = free_block_head;
	while(tmp != NULL){
		tmpx = tmp->next;
		while (tmpx != NULL){
			if (tmpx->start_addr < tmp->start_addr){
				swap(&tmp->start_addr, &tmpx->start_addr);
				swap(&tmp->size, &tmpx->size);
			}
			tmpx = tmpx->next;
		}
		tmp = tmp->next;
	}
	usleep(500);
	puts("Rearrange Done.");
}

void rearrange1(){ //将块大小进行排序
	free_block *tmp, *tmpx;
	puts("Rearrange begins...");
	puts("Rearrange by size ascending...");
	tmp = free_block_head;
	while(tmp != NULL){
		tmpx = tmp->next;
		while (tmpx != NULL){
			if (tmpx->size < tmp->size) {
				swap(&tmp->start_addr, &tmpx->start_addr);
				swap(&tmp->size, &tmpx->size);
			}
			tmpx = tmpx->next;
		}
		tmp = tmp->next;
	}
	usleep(500);
	puts("Rearrange Done.");
}

void rearrange2(){ //将块大小进行排序
	free_block *tmp, *tmpx;
	puts("Rearrange begins...");
	puts("Rearrange by size descending...");
	tmp = free_block_head;
	while(tmp != NULL){
		tmpx = tmp->next;
		while (tmpx != NULL){
			if (tmpx->size > tmp->size) {
				swap(&tmp->start_addr, &tmpx->start_addr);
				swap(&tmp->size, &tmpx->size);
			}
			tmpx = tmpx->next;
		}
		tmp = tmp->next;
	}
	usleep(500);
	puts("Rearrange Done.");
}



///can be modified
int free_mem(allocated_block *ab){ //释放某一块的内存
  rearrange();
  free_block *fb = free_block_head;
  free_block *new_fb;
  if (!fb->next && (ab->start_addr + ab->size == fb->start_addr
  || fb->start_addr + fb->size == ab->start_addr)) {
    if (fb->start_addr > ab->start_addr) {
      fb->start_addr = ab->start_addr;
    }
    fb->size += ab->size;
    return 0;
  }
  while (fb->next != NULL) {
    if (fb->start_addr + fb->size == ab->start_addr) {
      break;
    }
    fb = fb->next;
  }

  if (fb->next) {
    fb->size += ab->size;
    while (fb->next) {
      if (fb->start_addr + fb->size == fb->next->start_addr) {
       fb->size += fb->next->size; 
       free(fb->next);
       fb->next = fb->next->next;
      }
    }
  } else {
  new_fb = (free_block *)malloc(sizeof(free_block));
  if (new_fb == NULL){
	puts("No memory left");
    return -1;
  }
  new_fb->size = ab->size;
  new_fb->start_addr = ab->start_addr;
  new_fb->next = NULL;
  fb->next = new_fb;
  }
 return 0;
}

int dispose(allocated_block *fab){ //释放结构体所占的内存
	allocated_block *pre, *ab;
	if (fab == allocated_block_head){
		allocated_block_head = allocated_block_head->next;
		free(fab);
		return 1;
	}
	pre = allocated_block_head;
	ab = allocated_block_head->next;
	while (ab != fab){ pre = ab; ab = ab->next;}
	pre->next = ab->next;
	free(ab);//free(fab);
	return 2;
}

void display_mem_usage(){
    rearrange();
	free_block *fb = free_block_head;
	allocated_block *ab = allocated_block_head;
	puts("*********************Free Memory*********************");
	printf("%20s %20s\n", "start_addr", "size");
	int cnt = 0;
	while (fb != NULL && fb->size != 0){
		cnt++;
		printf("%20d %20d\n", fb->start_addr, fb->size);
		fb = fb->next;
	}
	if (!cnt) puts("No Free Memory");
	else printf("Totaly %d free blocks\n", cnt);
	puts("");
	puts("*******************Used Memory*********************");
	printf("%10s %20s %10s %20s\n", "PID", "ProcessName", "start_addr", "size");
	cnt = 0;
	while (ab != NULL){
		cnt++;
		printf("%10d %20s %10d %20d\n", ab->pid, ab->process_name, ab->start_addr, ab->size);
		ab = ab->next;
	}
	if (!cnt) puts("No allocated block");
	else printf("Totaly %d allocated blocks\n", cnt);
	return;
}

void kill_process(){ //杀死某个进程
	allocated_block *ab;
	int pid;
	puts("Please input the pid of Killed process");
	scanf("%d", &pid);
	ab = find_process(pid);
	if (ab != NULL){
		free_mem(ab);
		dispose(ab);
	} else {
    printf("Sorry the pid of your asigned process doesn't exist.\n");
    }
}


void Usemy_algo(int id) {

  if (algo[0].first == id) {
    actual_algo = algo[0];
  } else {
    actual_algo = algo[1];
  }

}
