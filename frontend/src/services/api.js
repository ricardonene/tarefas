import axios from "axios";
import { getToken } from "./auth";
import Configs from '../config/configs'

const api = axios.create({
  baseURL: `${Configs.API_URL}`
});

api.interceptors.request.use(async config => {
  const token = getToken();
  if (token) {
    config.headers.Authorization = `${token}`;
  }
  return config;
});

export default api;