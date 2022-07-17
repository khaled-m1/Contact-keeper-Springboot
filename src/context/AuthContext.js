import { Toast, useToast } from '@chakra-ui/react';
import { createContext, useState } from 'react';
import { Navigate, useNavigate } from 'react-router-dom';
import { errorToast, successToast } from '../util/toasts';

const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
  const toast = useToast();
  const [isLogged, setIsLogged] = useState(localStorage.getItem('isLogged'));
  const navigate = useNavigate();

  const login = async (username, password) => {
    const request = await fetch('/api/v1/auth/login', {
      headers: {
        Authorization: 'Basic ' + window.btoa(username + ':' + password),
      },
      method: 'POST',
    });
    const data = await request.json();

    if (request.status === 200) {
      toast(successToast(data.message));
      setIsLogged(1);
      localStorage.setItem('isLogged', 1);
      navigate('/');
    } else {
      toast(errorToast(data.message));
    }
  };

  const register = async (email, password, username, password2) => {
    if (password !== password2) {
      toast(errorToast('Passowd do not match !'));
      return;
    }

    const bodyData = JSON.stringify({ email, password, username });

    const request = await fetch('/api/v1/auth/register', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: bodyData,
    });
    const data = await request.json();

    if (request.status === 200) {
      toast(successToast(data.message));
      navigate('/login');
    } else {
      toast(errorToast(data.message));
    }
  };

  const logout = async () => {
    const request = await fetch('/api/v1/auth/logout');

    if (request.status === 204) {
      Toast(successToast('Logged Out successfully'));
      setIsLogged(0);
      localStorage.setItem('isLogged', 0);
      Navigate('/login');
    }
  };

  return (
    <AuthContext.Provider value={{ login, register, isLogged, logout }}>
      {children}
    </AuthContext.Provider>
  );
};
export default AuthContext;
