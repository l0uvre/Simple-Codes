#include <stdio.h>
#include <ctype.h>
#include <string.h>

#include "utf8.h"

#define LAN_LEN 50
#define BUFFER_LEN 10000 //if this program crashes, increase this value may solve it.
#define BLOCK_NUM 300

typedef struct block
{
  char lang_block[LAN_LEN];
  unsigned int begin_codepoint;
  unsigned int end_codepoint;
} U_BLOCK;


int find_block_index(U_BLOCK blocks[], int b_len, unsigned int codepoint);

char* trim(char *s);

int main(int argc, char const *argv[]) {

  //variable for loading blocks to struct U_BLOCK
  FILE *fp;
  char *line;
  char buffer[BUFFER_LEN];
  U_BLOCK blocks[BLOCK_NUM];
  int i = 0;

  // variable for scaning UTF-8 values
  unsigned char *tmp;
	char *l;
	char *p;
	char u_line[BUFFER_LEN];
  unsigned int codepoint;
  int bytes_in_char;


  int index;// the index for most common lang_block for the input stream

  fp = fopen("Blocks.txt", "r");

  if (!fp) {
    fprintf(stderr, "Sorry, can't open this file. Please put the \"Blocks.txt\" at the proper position.\n");
    return 1;
  }

  while ( (line = fgets(buffer, BUFFER_LEN, fp))!= NULL ) {
    if (line[0] == '#' || line[0] == ' ' || line[0] == '\n') {
      continue;
    }
    if (sscanf(line, "%X..%X; %[^\n]\n", &blocks[i].begin_codepoint, &blocks[i].end_codepoint, blocks[i].lang_block) == 3) {
      i++;
    } else {
      //i++;

      fprintf(stderr, "Failed to load the whole block.\n");
    }
  }

  fclose(fp);
  /***printf("%d blocks has been loaded.\n", i);

  for (int j = 0; j < i; j++) {
    printf("%X  | %X | %s\n", blocks[j].begin_codepoint, blocks[j].end_codepoint, blocks[j].lang_block);
  }***/

  int indice[i];

  for (int j = 0; j < i; j++) {
    indice[j] = 0;
  }

  while ((l = fgets(u_line, BUFFER_LEN, stdin)) != NULL) {
		l = trim(u_line);
		if (l) {
			while ((p = strchr(l, ' ')) != NULL) {
				if (p != NULL) {
					*p++ = '\0';
					//printf("%s\n", l);
					tmp = (unsigned char *)l;
					while (*tmp) {
						codepoint = utf8_to_codepoint(tmp, &bytes_in_char);
						if (codepoint) {
              index = find_block_index(blocks, i, codepoint);
              //printf("%d\n", index);
              indice[index]++;
							//printf("%c %u (%X) %d byte character\n", *tmp, codepoint, codepoint, bytes_in_char);
							_utf8_incr(tmp);
						}
            else {
              tmp++;
            }
					}

					l = p;
				}
			}

			tmp = (unsigned char *)l;
			while (*tmp) {
				codepoint = utf8_to_codepoint(tmp, &bytes_in_char);
				if (codepoint) {
          index = find_block_index(blocks, i, codepoint);
          indice[index]++;
					//printf("%c %u (%X) %d byte character\n", *tmp, codepoint, codepoint, bytes_in_char);
					_utf8_incr(tmp);
				}
        else {
          tmp++;
        }
			}
		}

	}


  int max = 0;
  for (int j = 0; j < i; j++) {
    if (max < indice[j]) {
      max = indice[j];
      index = j;
    }
  }


  printf("%s\n", blocks[index].lang_block);


  return 0;
}




int find_block_index(U_BLOCK blocks[], int b_len, unsigned int codepoint)
{
  int begin = 0;
  int end = b_len;
  int mid;



  while (begin <= end) {
    mid = begin + (end - begin) / 2;
    //printf("%d\n", mid);
    if (blocks[mid].begin_codepoint > codepoint) {
      end = mid - 1;

    }
    else if (blocks[mid].end_codepoint < codepoint) {
      begin = mid + 1;
    }
    else
    {
      break;
    }
  }

  return mid;

}

char* trim(char *s)
{
	char *p = s;
	int len;

	if (p) {
		len = strlen(p);
		if (p[len-1] == '\n') {
			p[len-1] = '\0';
		}

		while (isspace(*p)) {
			p++;
		}
		len = strlen(p);

		while ((*p) && isspace(p[len-1])) {
			len--;
		}
		p[len] = '\0';
	}

	return p;

}
