import { Resource } from "./resource";

export interface StockMapping{
    id?: number;
    resource: Resource;
    amount: number;
    value: number;
}