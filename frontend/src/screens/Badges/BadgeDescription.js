import React, {useEffect, useState} from "react";
import PropTypes from "prop-types";
import MedalImg from "../../assets/medal.png";
import {makeStyles} from "@material-ui/core/styles";
import {Button, Card, Typography} from "@material-ui/core";
import * as api from "../../api";

const BadgeDescription = ({badgeId, onReturnRequest}) => {
  const classes = useStyles();

  const [badge, setBadge] = useState(null);
  useEffect(() => {
    api.getBadge(badgeId).then(response => setBadge(response.data));
  }, [badgeId]);

  console.log(badge);

  if (!badge) return null;

  return (
    <div className={classes.root}>
      <img className={classes.medalImg} src={MedalImg} />
      <div className={classes.topBox}>
        <div>
          <Typography className={classes.titles} variant="h5">
            Odznaka: {badge.rank.badge.kind}
          </Typography>
          <Typography variant="h6">Stopień: {badge.rank.rank}</Typography>
          <Typography>Opis: {badge.rank.badge.description}</Typography>
        </div>
        <div>
          <Button color="primary" onClick={onReturnRequest}>
            Powrót
          </Button>
        </div>
      </div>
    </div>
  );
};

const useStyles = makeStyles({
  root: {
    display: "flex"
  },

  medalImg: {
    width: 200,
    marginRight: 50
  },

  topBox: {
    display: "flex",
    justifyContent: "space-between",
    width: "100%"
  }
});

export default BadgeDescription;
