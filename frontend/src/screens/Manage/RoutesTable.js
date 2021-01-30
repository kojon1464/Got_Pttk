import React from "react";
import TableRow from "@material-ui/core/TableRow";
import TableCell from "@material-ui/core/TableCell";
import {Button} from "@material-ui/core";
import IconButton from "@material-ui/core/IconButton";
import DeleteIcon from "@material-ui/icons/Delete";
import TableContainer from "@material-ui/core/TableContainer";
import Table from "@material-ui/core/Table";
import TableHead from "@material-ui/core/TableHead";
import TableBody from "@material-ui/core/TableBody";

const RoutesTable = ({routes, onModifyRequest}) => {
  const renderedRows = routes.map(route => (
    <TableRow key={route.route.id}>
      <TableCell>{route.route.start.name}</TableCell>
      <TableCell>{route.route.end.name}</TableCell>
      <TableCell>{route.route.description}</TableCell>
      <TableCell>Karkonosze</TableCell>
      <TableCell>Sudety</TableCell>
      <TableCell>{route.currentState?.pointsThere}</TableCell>
      <TableCell>{route.currentState?.pointsBack}</TableCell>
      <TableCell>
        <Button id='modify' onClick={() => onModifyRequest(route)}>Modyfikuj</Button>
        <IconButton>
          <DeleteIcon />
        </IconButton>
      </TableCell>
    </TableRow>
  ));

  return (
    <TableContainer>
      <Table>
        <TableHead>
          <TableRow>
            <TableCell>Start</TableCell>
            <TableCell>Koniec</TableCell>
            <TableCell>Opis</TableCell>
            <TableCell>Grupa górska</TableCell>
            <TableCell>Teren górski</TableCell>
            <TableCell>Pkt. tam</TableCell>
            <TableCell>Pkt. pow.</TableCell>
            <TableCell />
          </TableRow>
        </TableHead>
        <TableBody>
          <>{renderedRows}</>
        </TableBody>
      </Table>
    </TableContainer>
  );
};

export default RoutesTable;
