# 1st letter: c = compile, r = run

CC = javac12
J = java12

all: cs cc cl
# Add ParserTester once it has been made
#all: cs cc cl cp

# Server:

cs:
	$(CC) chat/server/*.java

rs:
	$(J) chat/server/ChatServer

crs: all rs

# Client:

cc:
	$(CC) chat/client/*.java

rc:
	$(J) chat/client/ChatClient

crc: all rc


# Libs

cl:
	$(CC) chat/libs/*.java chat/libs/protocol/*.java

# Lexer:

cx: cl
	$(CC) chat/libs/protocol/lexer/LexerTester.java

rx:
	$(J) chat/libs/protocol/lexer/LexerTester
	cat tokens.txt

crx: cx rx

# Parser:

cp: cl
	$(CC) chat/libs/protocol/parser/ParserTester.java

rp:
	$(CC) chat/libs/protocol/parser/ParserTester

crp: cp rp
