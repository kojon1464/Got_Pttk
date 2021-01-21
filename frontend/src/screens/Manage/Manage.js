import ManageContainer from "../../components/ManageContainer";
import RouteDescription from "./RouteDescription";
import React, {useState, useEffect} from "react";
import RoutesTable from "./RoutesTable";
import * as api from "../../api";

const Manage = () => {
  const [routes, setRoutes] = useState([]);
  useEffect(() => {
    api.getRoutes().then(res => setRoutes(res.data));
  }, []);

  const [modifiedRoute, setModifiedRoute] = useState(null);

  return (
    <ManageContainer>
      {!modifiedRoute && (
        <RoutesTable routes={routes} onModifyRequest={setModifiedRoute} />
      )}
      {modifiedRoute && (
        <RouteDescription
          route={modifiedRoute}
          onCancelRequest={() => setModifiedRoute(null)}
        />
      )}
    </ManageContainer>
  );
};

export default Manage;
