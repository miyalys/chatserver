# 1st letter: c = compile, r = run

CC = javac12
J = java12

all: cs cc cl


# Server:
cs:
	$(CC) chat/server/*.java

rs:
	$(J) chat/server/ChatServer

crs: cs rs


# Client:
cc:
	$(CC) chat/client/*.java

rc:
	$(J) chat/client/ChatClient

crc: cc rc


# Libs:
cl:
	$(CC) chat/libs/*.java chat/libs/*.java
	$(CC) chat/libs/*.java chat/libs/protocol/*.java
	$(CC) chat/libs/*.java chat/libs/protocol/lexer/*.java
	$(CC) chat/libs/*.java chat/libs/protocol/parser/*.java


# Lexer:
rx:
	$(J) chat/libs/protocol/lexer/LexerTester
	cat tokens.txt

crx: cl rx


# Parser:
rp:
	$(CC) chat/libs/protocol/parser/ParserTester

crp: cl rp
