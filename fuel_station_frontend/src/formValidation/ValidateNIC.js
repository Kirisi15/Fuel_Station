export const ValidateNIC = (value) => {
    const oldNicRegex = /^[0-9]{9}[vV]$/;
    const newNicRegex = /^[0-9]{12}$/;
    return oldNicRegex.test(value) || newNicRegex.test(value);
  };