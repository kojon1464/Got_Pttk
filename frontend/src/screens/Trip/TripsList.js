import React from "react";
import {makeStyles} from "@material-ui/core/styles";
import {Typography} from "@material-ui/core";
import Link from "@material-ui/core/Link";

const TripsList = ({trips}) => {
  const classes = useStyles();
  const preventDefault = event => event.preventDefault();

  return (
    <div className={classes.tripsList}>
      <Typography variant="h6" className={classes.tripsTitle}>
        Twoje wycieczki
      </Typography>
      {trips.map(trip => (
        <Link className={classes.tripButton} onClick={preventDefault}>
          {trip}
        </Link>
      ))}
    </div>
  );
};

const useStyles = makeStyles({
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

export default TripsList;
