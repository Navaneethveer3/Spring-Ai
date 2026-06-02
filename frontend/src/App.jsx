import { useState, useRef, useEffect } from 'react'
import './index.css'

function App() {
  const [messages, setMessages] = useState([
    { 
      role: 'bot', 
      content: 'System initialized. I am your Backend Architect AI.\n\nI specialize in system design, microservices, databases, and scalable architecture. How can I assist you with your Spring Boot application today?' 
    }
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
      setMessages(prev => [...prev, { role: 'bot', content: 'Connection Refused: I encountered an error connecting to the Spring Boot backend. Please ensure the server is running on port 8080.' }]);
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <div className="chat-container">
      <div className="chat-header">
        <div className="header-content">
          <h1>
            <svg className="icon" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round" style={{width: '28px', height: '28px', color: '#38bdf8'}}>
              <path d="M12 2v20M17 5H9.5a3.5 3.5 0 0 0 0 7h5a3.5 3.5 0 0 1 0 7H6"/>
            </svg>
            Backend Architect AI
          </h1>
          <p>Local Model &bull; Spring Boot Engine</p>
        </div>
        <div className="status-badge">
          <div className="status-dot"></div>
          System Online
        </div>
      </div>
      
      <div className="chat-messages">
        {messages.map((msg, idx) => (
          <div key={idx} className={`message ${msg.role}`}>
            <span className="message-label">{msg.role === 'user' ? 'Client' : 'Architect'}</span>
            <div className="message-bubble">
              {msg.content}
            </div>
          </div>
        ))}
        
        {isLoading && (
          <div className="message bot">
            <span className="message-label">Architect</span>
            <div className="message-bubble">
              <div className="loading-container">
                <div className="loading-dots">
                  <span></span>
                  <span></span>
                  <span></span>
                </div>
                <span className="loading-text">Synthesizing architecture...</span>
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
            placeholder="Describe your architectural requirements..."
            disabled={isLoading}
            autoFocus
          />
          <button type="submit" disabled={!input.trim() || isLoading}>
            <span>Deploy</span>
            <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round" className="icon" style={{width: '18px', height: '18px'}}>
              <line x1="22" y1="2" x2="11" y2="13"></line>
              <polygon points="22 2 15 22 11 13 2 9 22 2"></polygon>
            </svg>
          </button>
        </div>
      </form>
    </div>
  )
}

export default App
