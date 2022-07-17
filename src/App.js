import React from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Login from './Pages/Login';
import Register from './Pages/Register';
import Home from './Pages/Home';
import About from './Pages/About';
import NavBar from './components/Navbar';
import { AuthProvider } from './context/AuthContext';
import RequireAuth from './components/RequireAuth';
function App() {
  return (
    <BrowserRouter>
      <AuthProvider>
        <NavBar />
        <Routes>
          <Route element={<RequireAuth />}>
            <Route path="/" element={<Home />} />
          </Route>
          <Route path="/login" element={<Login />} />
          <Route path="/register" element={<Register />} />
          <Route path="/about" element={<About />} />
        </Routes>
      </AuthProvider>
    </BrowserRouter>
  );
}

export default App;
