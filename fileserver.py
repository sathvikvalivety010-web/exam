import socket
import os

def file_server():
    host = "127.0.0.1"   # localhost
    port = 8000

    # 1. Create server socket
    server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    server_socket.bind((host, port))
    server_socket.listen(1)
    print("Server is waiting for a client to connect...")

    # 2. Accept client connection
    conn, addr = server_socket.accept()
    print(f"Client connected: {addr}")

    # 3. Receive requested file name from client
    file_name = conn.recv(1024).decode()
    print(f"Client requested file: {file_name}")

    # 4. Send the file if it exists
    if os.path.exists(file_name):
        with open(file_name, "rb") as f:
            file_data = f.read()
            conn.sendall(file_data)
        print(f"File '{file_name}' sent to client.")
    else:
        error_message = "File not found!"
        conn.send(error_message.encode())
        print(f"File not found: {file_name}")

    # 5. Close connections
    conn.close()
    server_socket.close()

if __name__ == "__main__":
    file_server()
