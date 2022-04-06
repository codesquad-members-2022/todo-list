export const toggleActivation = () => {
  let activation = false;
  return {
    get: () => {
      return activation;
    },
    set: () => {
      activation = !activation;
      toggleActivation.toggleSidebar();
    },
  };
};
