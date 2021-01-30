import {makeStyles} from "@material-ui/core/styles";
import {Container} from "@material-ui/core";
import TripsList from "./TripsList";
import React from "react";

const Trip = () => {
  const classes = useStyles();

  return (
    <Container className={classes.root} maxWidth="lg">
      <TripsList trips={["Kurwa"]} />
      <div>wefw</div>
    </Container>
  );
};

const useStyles = makeStyles({
  root: {
    display: "flex",
    marginTop: 80
  },

  tripsList: {
    display: "flex",
    flexDirection: "column",
    marginRight: 100
  },
  tripsTitle: {
    marginBottom: 15
  },
  tripButton: {
    width: "100%",
    paddingTop: 10,
    paddingBottom: 10,
    cursor: "pointer"
  }
});

export default Trip;
