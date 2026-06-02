import { useState, useRef, useEffect } from 'react'
import './index.css'

function App() {
  const [messages, setMessages] = useState([
    { role: 'bot', content: 'Hello! I am your AI assistant. How can I help you today?' }
  ]);
  const [input, setInput] = useState('');
  const [isLoading, setIsLoading] = useState(false);
  const messagesEndRef = useRef(null);

  const scrollToBottom = () => {
    messagesEndRef.current?.scrollIntoView({ behavior: "smooth" });
  }

  useEffect(() => {
    scrollToBottom();
  }, [messages, isLoading]);

  const handleSend = async (e) => {
    e.preventDefault();
    if (!input.trim() || isLoading) return;

    const userMessage = input.trim();
    setInput('');
    setMessages(prev => [...prev, { role: 'user', content: userMessage }]);
    setIsLoading(true);

    try {
      const response = await fetch(`http://localhost:8080/chat/ask?prompt=${encodeURIComponent(userMessage)}`);
      
      if (!response.ok) {
        throw new Error(`HTTP error! status: ${response.status}`);
      }
      
      const data = await response.json();
      
      setMessages(prev => [...prev, { role: 'bot', content: data.response || "No response field in JSON." }]);
    } catch (error) {
      console.error("Error fetching response:", error);
      setMessages(prev => [...prev, { role: 'bot', content: 'Sorry, I encountered an error. Is the Spring Boot backend running?' }]);
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <div className="chat-container">
      <div className="chat-header">
        <div>
          <h1>Spring AI Chat</h1>
          <p>Powered by local models & Spring Boot</p>
        </div>
      </div>
      
      <div className="chat-messages">
        {messages.map((msg, idx) => (
          <div key={idx} className={`message ${msg.role}`}>
            <span className="message-label">{msg.role === 'user' ? 'You' : 'AI'}</span>
            <div className="message-bubble">
              {msg.content}
            </div>
          </div>
        ))}
        
        {isLoading && (
          <div className="message bot">
            <span className="message-label">AI</span>
            <div className="message-bubble">
              <div className="loading-dots">
                <span></span>
                <span></span>
                <span></span>
              </div>
            </div>
          </div>
        )}
        <div ref={messagesEndRef} />
      </div>

      <form className="chat-input-area" onSubmit={handleSend}>
        <div className="input-wrapper">
          <input
            type="text"
            value={input}
            onChange={(e) => setInput(e.target.value)}
            placeholder="Type a message to the AI..."
            disabled={isLoading}
          />
          <button type="submit" disabled={!input.trim() || isLoading}>
            Send
          </button>
        </div>
      </form>
    </div>
  )
}

export default App
