const errorToast = message => {
  return {
    position: 'top',
    title: message,
    status: 'error',
    duration: 2000,
  };
};
const successToast = message => {
  return {
    position: 'top',
    title: message,
    status: 'success',
    duration: 2000,
  };
};
export { errorToast, successToast };
