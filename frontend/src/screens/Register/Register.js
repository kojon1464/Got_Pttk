import {Button, TextField, Paper} from "@material-ui/core";
import {makeStyles} from "@material-ui/core/styles";
import WorldImg from "../../assets/world.png";
import React from "react";
import {Link} from "react-router-dom";

const Register = () => {
  const classes = useStyles();

  return (
    <div className={classes.root}>
      <div className={classes.leftBox}>
        <img className={classes.logoImg} src={WorldImg} />
        <h1>GOT PTTK</h1>
      </div>
      <div className={classes.rightBox}>
        <h1>Rejestracja</h1>
        <Paper className={classes.formContainer}>
          <TextField
            label="Adres email"
            variant="outlined"
            className={classes.input}
          />
          <div className={classes.formRow}>
            <TextField
              label="Imię"
              fullWidth
              variant="outlined"
              className={classes.input}
            />
            <TextField
              label="Nazwisko"
              fullWidth
              variant="outlined"
              className={classes.input}
            />
          </div>
          <TextField
            type="password"
            label="Hasło"
            variant="outlined"
            className={classes.input}
          />
          <Button
            variant="contained"
            color="primary"
            className={classes.submitButton}
            type="submit"
          >
            Zarejestruj
          </Button>
        </Paper>

        <span className={classes.link}>
          Masz już konto? <Link to="/">Zaloguj się</Link>
        </span>
      </div>
    </div>
  );
};

const useStyles = makeStyles({
  root: {
    display: "flex",
    flexDirection: "row",
    justifyContent: "center",
    paddingTop: 150
  },
  leftBox: {
    width: "50%",
    paddingRight: 50,
    display: "flex",
    flexDirection: "column",
    justifyContent: "center",
    alignItems: "center"
  },
  logoImg: {
    width: 250
  },
  rightBox: {
    width: "50%",
    display: "flex",
    flexDirection: "column",
    alignItems: "center"
  },
  formContainer: {
    display: "flex",
    flexDirection: "column",
    padding: 20
  },
  input: {
    margin: 10
  },
  formRow: {
    display: "flex"
  },
  submitButton: {
    marginTop: 15
  },
  link: {
    marginTop: 20
  }
});

export default Register;
