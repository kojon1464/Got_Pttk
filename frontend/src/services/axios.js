import {BACKEND_URL} from "../config";
import defAxios from "axios";

const getAuthToken = () => localStorage.getItem("auth") || "";

export const authHeader = () => ({
  headers: {
    Authorization: `Bearer ${getAuthToken()}`
  }
});

export const axios = defAxios.create({
  baseURL: BACKEND_URL
});
