import { Product } from "./product.entity";

export interface Order{
    id: string
    client_id: string
    products: Product[]
    menus: Product[]
}