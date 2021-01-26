import React from "react";
import {Card, Typography} from "@material-ui/core";
import MedalImg from "../../assets/medal.png";
import {makeStyles} from "@material-ui/core/styles";

const BadgeCard = ({badge, onClick}) => {
  const classes = useStyles();

  return (
    <Card id='badge' className={classes.card} onClick={() => onClick && onClick(badge)}>
      <img className={classes.medalImg} src={MedalImg} />
      <Typography className={classes.titles} variant="h6">
        {badge.badge.kind}
      </Typography>
      <Typography>{badge.rank}</Typography>
    </Card>
  );
};

const useStyles = makeStyles({
  card: {
    display: "flex",
    flexDirection: "column",
    alignItems: "center",
    padding: 24,
    cursor: "pointer"
  },

  medalImg: {
    width: "70%"
  },

  titles: {
    marginTop: 28
  }
});

export default BadgeCard;
