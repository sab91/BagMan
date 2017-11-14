package fr.utt.if26.mytravel.DAO;

import android.content.Context;
import android.database.Cursor;
import android.support.test.InstrumentationRegistry;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;

import fr.utt.if26.mytravel.Bdd;
import fr.utt.if26.mytravel.Page;

import static org.junit.Assert.*;

/**
 * Created by paf on 14/11/17.
 *
 * ============ Pour executer des tests : click droit sur le package de test (fr.utt.if26.<Nom> (androidTest)) et "Run..."======================
 *
 * Test un peu chelous
 * Diffère d'un test unitaire car il a besoin d'acceder au Contexte de l'application
 * En l'occurence la il a besoin d'emuler un device pour generer la base de données et direct parler avec
 *
 */
public class PageDAOTest {

    private Page page1;
    private Page page2;
    private Page page3;
    private Page page4;
    private Bdd bd;
    private PageDAO pdao;
    private Cursor c;


    /**
     * Initialisation des tests
     * On instancie les connexions avec les autres composants
     * On créé les objets dont on a besoin
     */
    @Before
    public void setUp() throws Exception {
        Context ct = InstrumentationRegistry.getTargetContext();
        bd = new Bdd(ct);
        pdao = new PageDAO(bd);
        // vidage de la table si il en existe une (visiblement la bdd ne se reinitialise pas une fois émulée)
        pdao.emptyTable();
        //Création des objets qu'on va manipuler
        page1 = new Page("title1", "content1", "summary1");
        page2 = new Page("title2", "content2", "summary2");
        page3 = new Page("title3", "content3", "summary3");
        page4 = new Page("title4", "content4", "summary4");

        // On les insere dans la bdd en récuperant l'ID généré
        page1.setId(pdao.insertRow(page1));
        page2.setId(pdao.insertRow(page2));
        page3.setId(pdao.insertRow(page3));
        page4.setId(pdao.insertRow(page4));
    }


    /**
     * Ce qui s'execute à la fin de tous les tests
     * Ici on ferme la connexion avec la base de données
     * @throws Exception
     */
    @After
    public void tearDown() throws Exception {
        bd.close();
    }

    /**
     *  Test de la fonction inserRow de PageDao
     *  On s'assure que toutes les pages qu'on a insérées dans la méthode setUp sont bien là
     * @throws Exception
     */
    @Test
    public void insertRow() throws Exception {
        assertEquals(1, page1.getId());
        assertEquals(2, page2.getId());
        assertEquals(3, page3.getId());
        assertEquals(4, page4.getId());
    }

    /**
     * Test de la fonction de chopage d'une page précise
     * Je n'ai pas trouver d'autres choix de comparer attributs par attributs
     * Peut être trouver un moyen plus propre pour gerer ça entre objets directs
     * @throws Exception
     */
    @Test
    public void getRow() throws Exception {
        Page p1 = pdao.getRow(1);

        assertEquals(page1.getId(), p1.getId());
        assertEquals(page1.getTitle(), p1.getTitle());
        assertEquals(page1.getContent(), p1.getContent());
        assertEquals(page1.getSummary(), p1.getSummary());
    }

    /**
     * Test de la fonction de récuperation de la liste de toutes les pages
     * On se contente de controller la taille de la liste
     * et de voir si tous les objets sont là
     * @throws Exception
     */
    @Test
    public void getList() throws Exception {
        assertTrue(pdao.getList().size() > 0);

        Page p1 = pdao.getRow(1);
        assertEquals(page1.getId(), p1.getId());
        assertEquals(page1.getTitle(), p1.getTitle());
        assertEquals(page1.getContent(), p1.getContent());
        assertEquals(page1.getSummary(), p1.getSummary());

        Page p2 = pdao.getRow(2);
        assertEquals(page2.getId(), p2.getId());
        assertEquals(page2.getTitle(), p2.getTitle());
        assertEquals(page2.getContent(), p2.getContent());
        assertEquals(page2.getSummary(), p2.getSummary());

        Page p3 = pdao.getRow(3);
        assertEquals(page3.getId(), p3.getId());
        assertEquals(page3.getTitle(), p3.getTitle());
        assertEquals(page3.getContent(), p3.getContent());
        assertEquals(page3.getSummary(), p3.getSummary());

        Page p4 = pdao.getRow(4);
        assertEquals(page4.getId(), p4.getId());
        assertEquals(page4.getTitle(), p4.getTitle());
        assertEquals(page4.getContent(), p4.getContent());
        assertEquals(page4.getSummary(), p4.getSummary());
    }

    /**
     * Suppression d'une Page de la bdd
     * Vérification à la barbare en chopant les ids des pages qu'on peut lister dans la bdd
     * Voir si pas moyen plus clean
     * @throws Exception
     */
    @Test
    public void deleteRow() throws Exception {
        ArrayList<Page> plist = pdao.getList();
        ArrayList i = new ArrayList();
        assertTrue(plist.size() == 4);

        pdao.deleteRow(page2);

        plist = pdao.getList();
        assertTrue(plist.size() == 3);

        Iterator<Page> it = plist.iterator();
        while (it.hasNext()) {
            Page pp = it.next();
            i.add(pp.getId());
        }

        assertFalse(i.contains(2));
    }
}