# Chatroom

This project is a simple chat application that consists of a server and client components written in Java. The chat server allows multiple clients to connect and exchange messages in a real-time chat environment.

# Features
- Multiple clients can connect to the server simultaneously.
- Clients can send messages that are broadcasted to all connected clients.
- Clients can set their names when connecting to the server.

# Getting Started
1. Ensure you have Java installed on your system.
2. Compile the `Server.java` and `Client.java` files using `javac Server.java` and `javac Client.java`.
3. Start the server by running `java Server`.
4. Start the client by running `java Client`.
5. Enter your name when prompted in the client console to start chatting.

# Server class
The `Server` class listens on a specific port (1234 by default) for incoming connections from clients. It handles client connections in separate threads and broadcasts messages to all connected clients.

# Client class
The `Client` class establishes a connection to the chat server. It allows users to send messages to the server, receive messages from other clients, and set their display name.

# A note
- Make sure to run the server before starting any clients for successful communication.
  
