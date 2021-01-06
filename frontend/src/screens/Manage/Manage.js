import React from "react";
import {makeStyles} from "@material-ui/core/styles";
import ManageContainer from "../../components/ManageContainer";
import TableContainer from "@material-ui/core/TableContainer";
import Table from "@material-ui/core/Table";
import TableHead from "@material-ui/core/TableHead";
import TableRow from "@material-ui/core/TableRow";
import TableCell from "@material-ui/core/TableCell";
import TableBody from "@material-ui/core/TableBody";
import {Button} from "@material-ui/core";
import IconButton from "@material-ui/core/IconButton";
import DeleteIcon from "@material-ui/icons/Delete";

const mountains = ["Kurwa", "Jebana", "Mać"];

const Manage = () => {
  const classes = useStyles();

  return (
    <ManageContainer>
      <div className={classes.root}>
        <TableContainer className={classes.tableContainer}>
          <Table className={classes.table} aria-label="simple table">
            <TableHead>
              <TableRow>
                <TableCell>Teren górski</TableCell>
                <TableCell>Akcje</TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {mountains.map(mountain => (
                <TableRow key={mountain}>
                  <TableCell width={250} component="th" scope="row">
                    {mountain}
                  </TableCell>
                  <TableCell>
                    <Button>Modyfikuj</Button>
                    <IconButton>
                      <DeleteIcon />
                    </IconButton>
                  </TableCell>
                </TableRow>
              ))}
            </TableBody>
          </Table>
        </TableContainer>

        <TableContainer className={classes.tableContainer}>
          <Table className={classes.table} aria-label="simple table">
            <TableHead>
              <TableRow>
                <TableCell>Teren górski</TableCell>
                <TableCell>Grupa górska</TableCell>
                <TableCell>Akcje</TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {mountains.map(mountain => (
                <TableRow key={mountain}>
                  <TableCell width={250} component="th" scope="row">
                    {mountain}
                  </TableCell>
                  <TableCell width={250} component="th" scope="row">
                    {mountain}
                  </TableCell>
                  <TableCell>
                    <Button>Modyfikuj</Button>
                    <IconButton>
                      <DeleteIcon />
                    </IconButton>
                  </TableCell>
                </TableRow>
              ))}
            </TableBody>
          </Table>
        </TableContainer>
      </div>
    </ManageContainer>
  );
};

Manage.propTypes = {};

const useStyles = makeStyles({
  root: {
    display: "flex",
    flexDirection: "row"
  },
  tableContainer: {
    width: "50%",
    paddingLeft: 10,
    paddingRight: 10
  }
});

export default Manage;
