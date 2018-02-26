CC       = g++
CFLAGS   = -Wall -g3 -O3 -ggdb -I/opt/local/include
LDFLAGS += -lgmp 
SOURCE   = $(shell find * -name '*.cpp')
OBJECTS  = $(SOURCE:.cpp=.o)
.PHONY:    clean

all: prog clean

prog: $(OBJECTS)
	$(CC) -o prog $^ $(LDFLAGS)

clean:
	rm -f $(OBJECTS)

