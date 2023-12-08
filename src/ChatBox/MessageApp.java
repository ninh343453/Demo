package ChatBox;

import java.util.Scanner;

    class Stack {
        private String[] stack;
        private int top;

        public Stack() {
            stack = new String[20];
            top = -1;
        }

        public void push(String message) {
            if (top < stack.length - 1) {
                stack[++top] = message;
            } else {
                System.out.println("Ngăn xếp đầy, không thể thêm tin nhắn.");
            }
        }

        public String pop() {
            if (top >= 0) {
                String message = stack[top];
                stack[top--] = null;
                return message;
            } else {
                System.out.println("Ngăn xếp rỗng, không thể lấy tin nhắn.");
                return null;
            }
        }

        public boolean isEmpty() {
            return top == -1;
        }
    }

    class Queue {
        private String[] queue;
        private int front;
        private int rear;
        private int size;

        public Queue() {
            queue = new String[20];
            front = 0;
            rear = -1;
            size = 0;
        }

        public void offer(String message) {
            if (size < queue.length) {
                rear = (rear + 1) % queue.length;
                queue[rear] = message;
                size++;
            } else {
                System.out.println("Hàng đợi đầy, không thể thêm tin nhắn.");
            }
        }

        public String poll() {
            if (size > 0) {
                String message = queue[front];
                queue[front] = null;
                front = (front + 1) % queue.length;
                size--;
                return message;
            } else {
                System.out.println("Hàng đợi rỗng, không thể lấy tin nhắn.");
                return null;
            }
        }

        public boolean isEmpty() {
            return size == 0;
        }
    }

    public class MessageApp {
        public static void main(String[] args) {
            Queue messageQueue = new Queue();
            Stack messageStack = new Stack();

            System.out.println("Nhập số lượng tin nhắn (1-20 tin):");
            Scanner scanner = new Scanner(System.in);
            int messageCount = scanner.nextInt();
            scanner.nextLine(); // Đọc ký tự new line sau khi nhập số lượng tin

            if (messageCount < 1 || messageCount > 20) {
                System.out.println("Số lượng tin nhắn không hợp lệ.");
                return;
            }

            System.out.println("Nhập các tin nhắn (tối đa 250 kí tự):");
            for (int i = 0; i < messageCount; i++) {
                System.out.print("Tin nhắn " + (i + 1) + ": ");
                String message = scanner.nextLine();
                if (message.length() <= 250) {
                    messageQueue.offer(message);
                } else {
                    System.out.println("Tin nhắn vượt quá 250 kí tự, bỏ qua.");
                }
            }

            scanner.close();

            System.out.println("\nCác tin nhắn đã nhập:");
            for (int i = 0; i < messageCount; i++) {
                String message = messageQueue.poll();
                messageStack.push(message);
                System.out.println(message);
            }

            System.out.println("\nCác tin nhắn từ ngăn xếp:");
            while (!messageStack.isEmpty()) {
                String message = messageStack.pop();
                System.out.println(message);
            }
        }
    }

