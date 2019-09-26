# 1st letter: c = compile, r = run
# 2nd letter: c = client, l = libs, s = server

crs: all rs

crc: all rc

all: cl cc cs

rc:
	java12 chat/client/ChatClient

rs:
	java12 chat/server/ChatServer

cl:
	javac12 chat/libs/protocol/*.java
	javac12 chat/libs/*.java

cc:
	javac12 chat/client/*.java

cs:
	javac12 chat/server/*.java
