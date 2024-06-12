import asyncio
import websockets
import json

async def send_receive_messages(uri):
    async with websockets.connect(uri) as websocket:
        while True:
            # Prompt user to enter a message
            message = input("Enter message to send to server (or type 'exit' to quit): ")

            if message.lower() == 'exit':
                break

            # Check if the message is in JSON format
            try:
                json_message = json.loads(message)
                message_with_timestamp = json.dumps({
                    **json_message
                })
            except json.JSONDecodeError:
                # If not in JSON format, send as is
                message_with_timestamp = message

            # Send the message to the server
            await websocket.send(message_with_timestamp)

            # Receive and print the response
            response = await websocket.recv()
            print(f" {response}")

if __name__ == "__main__":
    asyncio.get_event_loop().run_until_complete(send_receive_messages("ws://localhost:8080/websocket"))
