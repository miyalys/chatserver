# 1st letter: c = compile, r = run
# 2nd letter: c = client, l = libs, s = server, le = lexer

CC = javac12
J = java12

crs: all rs

crc: all rc

crl: cle rle

all: cl cc cs

cs:
	$(CC) chat/server/*.java

cc:
	$(CC) chat/client/*.java

cl:
	$(CC) chat/libs/protocol/*.java
	$(CC) chat/libs/*.java

cle:
	$(CC) chat/libs/protocol/LexerTester.java

rs:
	$(J) chat/server/ChatServer

rc:
	$(J) chat/client/ChatClient

rle:
	$(J) chat/libs/protocol/LexerTester
	cat tokens.txt
