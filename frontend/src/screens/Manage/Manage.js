import ManageContainer from "../../components/ManageContainer";
import {useAsync} from "react-async";
import * as api from "../../api";
import React, {useEffect} from "react";

const Manage = () => {
  // const {data, isLoading} = useAsync({
  //   promiseFn: api.getRoutes
  //   onResolve: res => console.log("dwef", res)
  // });

  useEffect(() => {
    api.getRoutes().then(console.log).catch(console.log);
  });

  return (
    <ManageContainer>
      {/*{data}*/}
      {/*{isLoading ? "t" : "f"}*/}
    </ManageContainer>
  );
};

export default Manage;
