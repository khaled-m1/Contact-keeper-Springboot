import { HStack, Spacer, Text } from '@chakra-ui/react';
import {  useContext } from 'react';
import { Link } from 'react-router-dom';
import AuthContext from '../context/AuthContext';


const NavBar = () => {
  const { isLogged,logout } = useContext(AuthContext);

 

  return (
    <HStack backgroundColor="black" width="100vw" height="5vh" color="#fff">
      <Text fontSize="1.5rem" ml="3" fontWeight="bold" cursor="pointer">
        <Link to="/"> Raqami 📱</Link>
      </Text>
      <Spacer />
      <HStack marginRight="2rem !important" spacing="2rem" fontWeight="bold">
        {!isLogged && <Link to="/register">Register</Link>}
        {!isLogged && <Link to="/login">Login</Link>}
        {isLogged && (
          <Text cursor="pointer" onClick={logout}>
            Logout
          </Text>
        )}
        <Link to="/about">About</Link>
      </HStack>
    </HStack>
  );
};
export default NavBar;
