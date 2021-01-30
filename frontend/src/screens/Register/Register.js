import AuthContainer from "../../components/AuthContainer";
import {Button, TextField} from "@material-ui/core";
import {makeStyles} from "@material-ui/core/styles";
import {Link} from "react-router-dom";
import React, {useState} from "react";
import * as api from "../../api";
import EmailConfirmationInfo from "./EmailConfirmationInfo";

const Register = () => {
  const classes = useStyles();

  const [isRegistered, setIsRegistered] = useState(false);
  const [isEmailError, setIsEmailError] = useState(false);

  const [formData, setFormData] = useState({
    email: "",
    password: "",
    firstName: "",
    secondName: ""
  });
  const updateFormField = fieldName => event => {
    setFormData(prev => ({
      ...prev,
      [fieldName]: event.target.value
    }));
  };
  const isSomeFieldEmpty =
    !formData.email ||
    !formData.password ||
    !formData.firstName ||
    !formData.secondName;

  const handleRegisterSubmit = () => {
    return api
      .register(formData.email, formData.password)
      .then(() => setIsRegistered(true))
      .catch(() => setIsEmailError(true));
  };

  return (
    <AuthContainer
      title="Rejestracja"
      caption={
        <span>
          Masz już konto? <Link to="/">Zaloguj się</Link>
        </span>
      }
    >
      {isRegistered && <EmailConfirmationInfo />}
      {!isRegistered && (
        <>
          <TextField
            label="Adres email"
            variant="outlined"
            className={classes.input}
            value={formData.email}
            onChange={updateFormField("email")}
            error={isEmailError}
            helperText={isEmailError && "Podany adres został już użyty"}
          />
          <div className={classes.formRow}>
            <TextField
              label="Imię"
              fullWidth
              variant="outlined"
              className={classes.input}
              value={formData.firstName}
              onChange={updateFormField("firstName")}
            />
            <TextField
              label="Nazwisko"
              fullWidth
              variant="outlined"
              className={classes.input}
              value={formData.secondName}
              onChange={updateFormField("secondName")}
            />
          </div>
          <TextField
            type="password"
            label="Hasło"
            variant="outlined"
            className={classes.input}
            value={formData.password}
            onChange={updateFormField("password")}
          />
          <Button
            variant="contained"
            color="primary"
            className={classes.submitButton}
            type="submit"
            disabled={isSomeFieldEmpty}
            onSubmit={handleRegisterSubmit}
          >
            Zarejestruj
          </Button>
        </>
      )}
    </AuthContainer>
  );
};

const useStyles = makeStyles({
  input: {
    margin: 10
  },
  formRow: {
    display: "flex"
  },
  submitButton: {
    marginTop: 15
  }
});

export default Register;
