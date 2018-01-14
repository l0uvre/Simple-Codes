CC = g++
OBJS = utf8.o UTF8string.o testUTF8string.cpp 
LIBS = -lm
CFLAGS = -Wall
EXE = UTF8test

$(EXE): ${OBJS} 
	$(CC) $(CFLAGS) ${OBJS} -o $@ ${LIBS} 
%.o : %.c %.cpp
	${CC} ${CFLAGS} -c $< -o $@ ${LIBS} 
clean:
	rm -f *.o $(EXE) 
