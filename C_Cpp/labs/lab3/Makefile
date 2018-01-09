CC = gcc
OBJS = utf8.o process_utf8_content.c 
LIBS = -lm
CFLAGS = -Wall

lab3: ${OBJS} 
	$(CC) $(CFLAGS) ${OBJS} -o $@ ${LIBS} 
.c.o:
	${CC} ${CFLAGS} -c $<
clean:
	-rm -f *.o
