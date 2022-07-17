import { Flex, VStack, Text, Input, Button } from '@chakra-ui/react';
import React, { useContext, useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import AuthContext from '../context/AuthContext';

const Register = () => {
  const [username, setUsername] = useState('');
  const [email, setEmail] = useState('');

  const [password, setPassword] = useState('');
  const [password2, setPassword2] = useState('');
  const navigate = useNavigate();

  const { register, isLogged } = useContext(AuthContext);
  useEffect(() => {
    if (isLogged) {
      navigate('/');
    }
  }, []);
  const onClick = async () => {
    register(email, password, username, password2);
  };
  return (
    <Flex height="75vh" justifyContent="center" alignItems="center">
      <VStack
        border="1px solid black"
        width="40rem"
        spacing="1rem"
        padding="5"
        borderRadius="0.5rem"
      >
        <Text fontSize="3rem">Register 👋</Text>
        <Input
          onChange={e => setUsername(e.target.value)}
          value={username}
          placeholder="Username"
        />
        <Input
          onChange={e => setEmail(e.target.value)}
          value={email}
          placeholder="Email"
        />
        <Input
          onChange={e => setPassword(e.target.value)}
          value={password}
          type="password"
          placeholder="Password"
        />
        <Input
          onChange={e => setPassword2(e.target.value)}
          value={password2}
          type="password"
          placeholder="Confirm password"
        />
        <Button width="80%" p="5" fontSize="1.2rem" onClick={onClick}>
          Register !
        </Button>
      </VStack>
    </Flex>
  );
};

export default Register;
