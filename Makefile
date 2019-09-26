crs: all rs

crc: all rc

all: cp cc cs

rc:
	java12 chat/client/ChatClient

rs:
	java12 chat/server/ChatServer

cp:
	javac12 chat/protocol/*.java

cc:
	javac12 chat/client/*.java

cs:
	javac12 chat/server/*.java
