import React from "react";
import {Divider, Typography} from "@material-ui/core";

const PointsCounting = ({userPoints, userPointsExcessive}) => {
  return (
    <div style={{display: "flex", width: 350}}>
      <div style={{marginRight: 20}}>
        <Typography>Punkty zebrane w tym cyklu:</Typography>
        <Typography>Punkty z nadwyżki:</Typography>
      </div>

      <div style={{width: 80}}>
        <Typography>{userPoints}</Typography>
        <Typography>{userPointsExcessive}</Typography>
        <Divider />
        <Typography>{userPoints + userPointsExcessive}</Typography>
      </div>
    </div>
  );
};

export default PointsCounting;
