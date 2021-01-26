import React, {useState} from "react";
import {Button, Card, Typography, TextField} from "@material-ui/core";
import {makeStyles} from "@material-ui/core/styles";

const AddressForm = ({onSubmit}) => {
  const classes = useStyles();

  const [formData, setFormData] = useState({
    town: "",
    postalCode: "",
    street: "",
    buildingNumber: "",
    flatNumber: ""
  });
  const handleChange = fieldName => event =>
    setFormData(prev => ({...prev, [fieldName]: event.target.value}));

  const handleFormSubmit = () => onSubmit && onSubmit(formData);

  const isFormFulfilled = !Object.values(formData).every(val => val.length);

  return (
    <Card className={classes.root}>
      <Typography align="center" variant="subtitle1">
        Odbierz odznakę, poprzez uzupełnienie adresu wysyłki
      </Typography>
      <div className={classes.form}>
        <div className={classes.column} style={{marginRight: 50}}>
          <TextField
            id='building'
            label="Numer budynku"
            value={formData.buildingNumber}
            onChange={handleChange("buildingNumber")}
          />
          <TextField
          id='flat'
            label="Numer mieszkania"
            value={formData.flatNumber}
            onChange={handleChange("flatNumber")}
          />
          <TextField
          id='street'
            label="Ulica"
            value={formData.street}
            onChange={handleChange("street")}
          />
        </div>
        <div className={classes.column}>
          <TextField
          id='town'
            label="Miejscowość"
            value={formData.town}
            onChange={handleChange("town")}
          />
          <TextField
          id='code'
            label="Kod pocztowy"
            value={formData.postalCode}
            onChange={handleChange("postalCode")}
          />
          <Button
          id='confirm'
            color="primary"
            variant="contained"
            style={{marginTop: 18}}
            onClick={handleFormSubmit}
            disabled={isFormFulfilled}
          >
            Odbierz odznakę!
          </Button>
        </div>
      </div>
    </Card>
  );
};

const useStyles = makeStyles({
  root: {
    width: "60%",
    marginLeft: 10,
    padding: 20,
    display: "flex",
    flexDirection: "column",
    justifyContent: "center",
    alignItem: "center"
  },

  form: {
    display: "flex"
  },

  column: {
    display: "flex",
    flexDirection: "column",
    justifyContent: "center",
    alignItems: "center",
    width: "50%"
  }
});

export default AddressForm;
