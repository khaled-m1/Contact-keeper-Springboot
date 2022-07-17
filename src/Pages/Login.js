import { Flex, VStack, Text, Input, Button } from '@chakra-ui/react';
import React, { useContext, useEffect, useState } from 'react';
import {  useNavigate } from 'react-router-dom';
import AuthContext from '../context/AuthContext';


const Login = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const navigate = useNavigate();
  const { login, isLogged } = useContext(AuthContext);
  useEffect(() => {
    if (isLogged) {
      navigate('/');
    }
  }, []);
  
  const onClick = () => {
    login(username, password);
  };
  return (
    <Flex height="75vh" justifyContent="center" alignItems="center">
      <VStack
        border="1px solid black"
        height="25rem"
        width="40rem"
        spacing="1rem"
        padding="5"
        borderRadius="0.5rem"
      >
        <Text fontSize="3rem">Login 👋</Text>
        <Input
          onChange={e => setUsername(e.target.value)}
          value={username}
          placeholder="Username"
        />
        <Input
          onChange={e => setPassword(e.target.value)}
          value={password}
          type="password"
          placeholder="Password"
        />
        <Button width="80%" p="5" fontSize="1.2rem" onClick={onClick}>
          Login !
        </Button>
        <Text fontSize="1rem">We Use 🍪</Text>
      </VStack>
    </Flex>
  );
};

export default Login;
