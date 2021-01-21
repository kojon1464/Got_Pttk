import ManageContainer from "../../components/ManageContainer";
import React, {useState, useEffect} from "react";
import RoutesTable from "./RoutesTable";
import * as api from "../../api";

const Manage = () => {
  const [routes, setRoutes] = useState([]);
  useEffect(() => {
    api.getRoutes().then(res => setRoutes(res.data));
  }, []);

  return (
    <ManageContainer>
      <RoutesTable routes={routes} onModifyRequest={() => true} />
    </ManageContainer>
  );
};

export default Manage;
